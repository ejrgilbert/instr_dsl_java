package lang.dtrace.visitor;

import lang.dtrace.ast.DScript;
import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.ast.exp.binary.BinExp;
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
import lang.dtrace.ast.exp.binary.sum.AddExp;
import lang.dtrace.ast.exp.binary.sum.SubExp;
import lang.dtrace.ast.exp.unary.log.NotExp;
import lang.dtrace.ast.id.ProbeId;
import lang.dtrace.ast.id.VarId;
import lang.dtrace.ast.probe.Probe;
import lang.dtrace.ast.probe.ProbeSpec;
import lang.dtrace.ast.stmt.Stmt;
import lang.dtrace.ast.val.Int;
import lang.dtrace.ast.val.Str;

import java.util.List;

public class DtraceASTDumper implements DtraceVisitor<String> {
    private static final String NL = System.lineSeparator();

    private int indentationLevel = 0;

    private void increaseIndentation() {
        this.indentationLevel += 1;
    }

    private void decreaseIndentation() {
        this.indentationLevel -= 1;
    }

    private String getIndentation() {
        return "--".repeat(Math.max(0, indentationLevel));
    }

    @Override
    public String visitDScript(DScript dscriptNode) {
        StringBuilder builder = new StringBuilder();
        builder.append(NL);
        for (DtraceASTNode probe : dscriptNode.getProbes()) {
            increaseIndentation();
            builder.append("ProbeDefinition").append(NL);
            builder.append(probe.accept(this));
            decreaseIndentation();
        }
        return builder.toString();
    }

    @Override
    public String visitProbeId(ProbeId probeIdNode) {
        return getIndentation() + "ProbeId: " + probeIdNode.getId() + NL;
    }

    @Override
    public String visitVarId(VarId varIdNode) {
        return getIndentation() + "VarId: " + varIdNode.getId() + NL;
    }

    @Override
    public String visitProbe(Probe probeNode) {
        StringBuilder builder = new StringBuilder();
        builder.append(getIndentation()).append("Probe:").append(NL);
        increaseIndentation();
        // spec
        builder.append(getIndentation()).append("spec:").append(NL);
        increaseIndentation();
        builder.append(probeNode.getSpec().accept(this));
        decreaseIndentation();
        // predicate
        if (probeNode.getPredicate() != null) {
            builder.append(getIndentation()).append("predicate:").append(NL);
            increaseIndentation();
            builder.append(probeNode.getPredicate().accept(this));
            decreaseIndentation();
        }
        // body
        builder.append(getIndentation()).append("body:").append(NL);
        increaseIndentation();
        List<DtraceASTNode> body = probeNode.getBody();
        for (DtraceASTNode stmt : body) {
            builder.append(stmt.accept(this));
        }
        decreaseIndentation();

        return builder.toString();
    }

    @Override
    public String visitProbeSpec(ProbeSpec probeSpecNode) {
        StringBuilder builder = new StringBuilder();
        builder.append(getIndentation()).append("ProbeSpec:").append(NL);
        increaseIndentation();
        // provider
        builder.append(getIndentation()).append("provider:").append(NL);
        increaseIndentation();
        builder.append(probeSpecNode.getProvider().accept(this));
        decreaseIndentation();
        // module
        builder.append(getIndentation()).append("module:").append(NL);
        increaseIndentation();
        builder.append(probeSpecNode.getModule().accept(this));
        decreaseIndentation();
        // function
        builder.append(getIndentation()).append("function:").append(NL);
        increaseIndentation();
        builder.append(probeSpecNode.getFunction().accept(this));
        decreaseIndentation();
        // name
        builder.append(getIndentation()).append("name:").append(NL);
        increaseIndentation();
        builder.append(probeSpecNode.getName().accept(this));
        decreaseIndentation();

        decreaseIndentation();
        return builder.toString();
    }

    @Override
    public String visitStmt(Stmt stmtNode) {
        StringBuilder builder = new StringBuilder();
        builder.append(getIndentation()).append("Stmt:").append(NL);
        increaseIndentation();
        builder.append(getIndentation()).append("some stmt...guts not yet implemented");
        decreaseIndentation();
        return builder.toString();
    }

    private String visitBinExp(BinExp expNode, String operand) {
        StringBuilder builder = new StringBuilder();

        builder.append(getIndentation()).append("left:").append(NL);
        increaseIndentation();
        builder.append(expNode.getLeftOperand().accept(this));
        decreaseIndentation();
        builder.append(getIndentation()).append("operator: ").append(operand).append(NL);
        builder.append(getIndentation()).append("right:").append(NL);
        increaseIndentation();
        builder.append(expNode.getRightOperand().accept(this));
        decreaseIndentation();

        return builder.toString();
    }

    @Override
    public String visitAndExp(AndExp andExpNode) {
        String builder = getIndentation() + "AndExp:" + NL;
        increaseIndentation();

        builder += visitBinExp(andExpNode, "&&");

        decreaseIndentation();
        return builder;
    }

    @Override
    public String visitOrExp(OrExp orExpNode) {
        String builder = getIndentation() + "OrExp:" + NL;
        increaseIndentation();

        builder += visitBinExp(orExpNode, "||");

        decreaseIndentation();
        return builder;
    }

    @Override
    public String visitEqExp(EqExp eqExpNode) {
        String builder = getIndentation() + "EqExp:" + NL;
        increaseIndentation();

        builder += visitBinExp(eqExpNode, "==");

        decreaseIndentation();
        return builder;
    }

    @Override
    public String visitGTEqExp(GTEqExp gtEqExpNode) {
        String builder = getIndentation() + "GTEqExp:" + NL;
        increaseIndentation();

        builder += visitBinExp(gtEqExpNode, ">=");

        decreaseIndentation();
        return builder;
    }

    @Override
    public String visitGTExp(GTExp gtExpNode) {
        String builder = getIndentation() + "GTExp:" + NL;
        increaseIndentation();

        builder += visitBinExp(gtExpNode, ">");

        decreaseIndentation();
        return builder;
    }

    @Override
    public String visitLTEqExp(LTEqExp ltEqExpNode) {
        String builder = getIndentation() + "LTEqExp:" + NL;
        increaseIndentation();

        builder += visitBinExp(ltEqExpNode, "<=");

        decreaseIndentation();
        return builder;
    }

    @Override
    public String visitLTExp(LTExp ltExpNode) {
        String builder = getIndentation() + "LTExp:" + NL;
        increaseIndentation();

        builder += visitBinExp(ltExpNode, "<");

        decreaseIndentation();
        return builder;
    }

    @Override
    public String visitNEqExp(NEqExp nEqExpNode) {
        String builder = getIndentation() + "NEqExp:" + NL;
        increaseIndentation();

        builder += visitBinExp(nEqExpNode, "!=");

        decreaseIndentation();
        return builder;
    }

    @Override
    public String visitDivExp(DivExp divExpNode) {
        String builder = getIndentation() + "DivExp:" + NL;
        increaseIndentation();

        builder += visitBinExp(divExpNode, "/");

        decreaseIndentation();
        return builder;
    }

    @Override
    public String visitModExp(ModExp modExpNode) {
        String builder = getIndentation() + "ModExp:" + NL;
        increaseIndentation();

        builder += visitBinExp(modExpNode, "%");

        decreaseIndentation();
        return builder;
    }

    @Override
    public String visitMulExp(MulExp mulExpNode) {
        String builder = getIndentation() + "MulExp:" + NL;
        increaseIndentation();

        builder += visitBinExp(mulExpNode, "*");

        decreaseIndentation();
        return builder;
    }

    @Override
    public String visitAddExp(AddExp addExpNode) {
        String builder = getIndentation() + "AddExp:" + NL;
        increaseIndentation();

        builder += visitBinExp(addExpNode, "+");

        decreaseIndentation();
        return builder;
    }

    @Override
    public String visitSubExp(SubExp subExpNode) {
        String builder = getIndentation() + "SubExp:" + NL;
        increaseIndentation();

        builder += visitBinExp(subExpNode, "-");

        decreaseIndentation();
        return builder;
    }

    @Override
    public String visitNotExp(NotExp notExpNode) {
        String builder = getIndentation() + "NotExp:" + NL;

        increaseIndentation();
        builder += notExpNode.getOperand().accept(this);
        decreaseIndentation();

        return builder;
    }

    @Override
    public String visitInt(Int intNode) {
        return getIndentation() + "Int: " + intNode.getValue() + NL;
    }

    @Override
    public String visitString(Str stringNode) {
        return getIndentation() + "Str: " + stringNode.getValue() + NL;
    }
}
