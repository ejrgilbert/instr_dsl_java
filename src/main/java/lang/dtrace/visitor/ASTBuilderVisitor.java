package lang.dtrace.visitor;

import lang.dtrace.DtraceParser;
import lang.dtrace.DtraceParserBaseVisitor;
import lang.dtrace.ast.DScript;
import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.ast.exp.binary.log.AndExp;
import lang.dtrace.ast.exp.binary.log.OrExp;
import lang.dtrace.ast.exp.binary.mul.DivExp;
import lang.dtrace.ast.exp.binary.mul.ModExp;
import lang.dtrace.ast.exp.binary.mul.MulExp;
import lang.dtrace.ast.exp.binary.rel.EqExp;
import lang.dtrace.ast.exp.binary.rel.GTEqExp;
import lang.dtrace.ast.exp.binary.rel.GTExp;
import lang.dtrace.ast.exp.binary.rel.LTEqExp;
import lang.dtrace.ast.exp.binary.rel.LTExp;
import lang.dtrace.ast.exp.binary.rel.NEqExp;
import lang.dtrace.ast.exp.binary.sum.SubExp;
import lang.dtrace.ast.id.VarId;
import lang.dtrace.ast.probe.Probe;
import lang.dtrace.ast.probe.ProbeSpec;
import lang.dtrace.ast.val.Int;
import lang.dtrace.ast.val.Str;
import lang.dtrace.error.DtraceParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO
public class ASTBuilderVisitor extends DtraceParserBaseVisitor<DtraceASTNode> {
    private static final Logger logger = LoggerFactory.getLogger(ASTBuilderVisitor.class);

    // SUMOPs
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    // MULOPs
    public static final String TIMES = "*";
    public static final String DIVIDE = "/";
    public static final String MOD = "%";
    // RELOPs
    public static final String EQ = "==";
    public static final String NEQ = "!=";
    public static final String GT = ">";
    public static final String GT_EQ = ">=";
    public static final String LT = "<";
    public static final String LT_EQ = "<=";
    // LOGOPs
    public static final String AND = "&&";
    public static final String OR = "||";

    @Override
    public DtraceASTNode visitDscript(DtraceParser.DscriptContext ctx) {
        logger.debug("entering visitDscript");
        DScript dScript = new DScript();
        for (DtraceParser.Probe_defContext probeCtx : ctx.probe_def()) {
            DtraceASTNode probe = probeCtx.accept(this);
            dScript.addProbe(probe);
        }

        logger.debug("exiting visitDscript");
        return dScript;
    }

    @Override
    public DtraceASTNode visitProbe_def(DtraceParser.Probe_defContext ctx) {
        logger.debug("entering visitProbe_def");
        Probe probe = new Probe();

        // Handle ProbeSpec -- ProbeSpec spec
        DtraceASTNode probeSpec = ctx.spec().accept(this);
        // Handle Predicate -- Exp predicate
        DtraceASTNode predicate = ctx.predicate() != null ? ctx.predicate().accept(this) : null;
        // Handle Body -- List<Stmt> body
        for (DtraceParser.StatementContext stmtCtx : ctx.statement()) {
            DtraceASTNode stmt = stmtCtx.accept(this);
            probe.addStmtToBody(stmt);
        }

        probe.setSpec(probeSpec);
        probe.setPredicate(predicate);
        logger.debug("exiting visitProbe_def");
        return probe;
    }

    @Override
    public DtraceASTNode visitSpec(DtraceParser.SpecContext ctx) {
        logger.debug("entering visitSpec");

        String spec;
        if (ctx.ID() != null) {
            // process as ID
            spec = ctx.ID().getText();
        } else if (ctx.PROBE_ID() != null) {
            // process as PROBE_ID
            spec = ctx.PROBE_ID().getText();
        } else if (ctx.PROBE_SPEC() != null) {
            // process as PROBE_SPEC
            spec = ctx.PROBE_SPEC().getText();
        } else {
            throw new DtraceParseException("Something has gone terribly wrong...");
        }

        logger.debug("exiting visitSpec");
        return new ProbeSpec(spec);
    }

    @Override
    public DtraceASTNode visitPredicate(DtraceParser.PredicateContext ctx) {
        logger.debug("entering visitPredicate");

        DtraceASTNode exp = ctx.exp().accept(this);

        logger.debug("exiting visitPredicate");
        return exp;
    }

    private DtraceASTNode visitBinExp(String operand, DtraceASTNode left, DtraceASTNode right) {
        return switch (operand) {
            // Mul
            case TIMES -> new MulExp(left, right);
            case DIVIDE -> new DivExp(left, right);
            case MOD -> new ModExp(left, right);
            // Log
            case AND -> new AndExp(left, right);
            case OR -> new OrExp(left, right);
            // Sum
            case PLUS -> new AndExp(left, right);
            case MINUS -> new SubExp(left, right);
            // Rel
            case EQ -> new EqExp(left, right);
            case NEQ -> new NEqExp(left, right);
            case GT -> new GTExp(left, right);
            case GT_EQ -> new GTEqExp(left, right);
            case LT -> new LTExp(left, right);
            case LT_EQ -> new LTEqExp(left, right);
            default ->
                    throw new DtraceParseException("Something has gone terribly wrong: operand '" + operand + "' is unexpected.");
        };
    }

    @Override
    public DtraceASTNode visitMulExp(DtraceParser.MulExpContext ctx) {
        logger.debug("entering visitMulExp");
        String operand = ctx.getChild(1).getText();
        DtraceASTNode left = ctx.exp(0).accept(this);
        DtraceASTNode right = ctx.exp(1).accept(this);

        DtraceASTNode exp = visitBinExp(operand, left, right);

        logger.debug("exiting visitMulExp");
        return exp;
    }

    @Override
    public DtraceASTNode visitLogExp(DtraceParser.LogExpContext ctx) {
        logger.debug("entering visitLogExp");
        String operand = ctx.getChild(1).getText();
        DtraceASTNode left = ctx.exp(0).accept(this);
        DtraceASTNode right = ctx.exp(1).accept(this);

        DtraceASTNode exp = visitBinExp(operand, left, right);

        logger.debug("exiting visitLogExp");
        return exp;
    }

    @Override
    public DtraceASTNode visitSumExp(DtraceParser.SumExpContext ctx) {
        logger.debug("entering visitSumExp");
        String operand = ctx.getChild(1).getText();
        DtraceASTNode left = ctx.exp(0).accept(this);
        DtraceASTNode right = ctx.exp(1).accept(this);

        DtraceASTNode exp = visitBinExp(operand, left, right);

        logger.debug("exiting visitSumExp");
        return exp;
    }

    @Override
    public DtraceASTNode visitBaseExp(DtraceParser.BaseExpContext ctx) {
        logger.debug("entering visitBaseExp");

        DtraceASTNode exp;
        if (ctx.ID() != null) {
            // process as ID
            exp = new VarId(ctx.ID().getText());
        } else if (ctx.STRING() != null) {
            // process as STRING
            exp = new Str(ctx.STRING().getText());
        } else if (ctx.INT() != null) {
            // process as INT
            exp = new Int(ctx.INT().getText());
        } else {
            throw new DtraceParseException("Something has gone terribly wrong...");
        }

        logger.debug("exiting visitBaseExp");
        return exp;
    }

    @Override
    public DtraceASTNode visitRelExp(DtraceParser.RelExpContext ctx) {
        logger.debug("entering visitRelExp");
        String operand = ctx.getChild(1).getText();
        DtraceASTNode left = ctx.exp(0).accept(this);
        DtraceASTNode right = ctx.exp(1).accept(this);

        DtraceASTNode exp = visitBinExp(operand, left, right);

        logger.debug("exiting visitRelExp");
        return exp;
    }

    @Override
    public DtraceASTNode visitInnerExp(DtraceParser.InnerExpContext ctx) {
        logger.debug("entering visitInnerExp");

        DtraceASTNode exp = ctx.exp().accept(this);

        logger.debug("exiting visitInnerExp");
        return exp;
    }

    @Override
    public DtraceASTNode visitStatement(DtraceParser.StatementContext ctx) {
        logger.debug("entering visitStatement");

        DtraceASTNode exp = ctx.exp().accept(this);

        logger.debug("exiting visitStatement");
        return exp;
    }
}
