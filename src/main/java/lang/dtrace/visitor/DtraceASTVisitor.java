package lang.dtrace.visitor;

import lang.dtrace.ast.exp.binary.log.AndExpNode;
import lang.dtrace.ast.exp.binary.log.OrExpNode;
import lang.dtrace.ast.exp.binary.mul.DivExpNode;
import lang.dtrace.ast.exp.binary.mul.ModExpNode;
import lang.dtrace.ast.exp.binary.mul.MulExpNode;
import lang.dtrace.ast.exp.binary.rel.EqExpNode;
import lang.dtrace.ast.exp.binary.rel.GTEqExpNode;
import lang.dtrace.ast.exp.binary.rel.GTExpNode;
import lang.dtrace.ast.exp.binary.rel.LTEqExpNode;
import lang.dtrace.ast.exp.binary.rel.LTExpNode;
import lang.dtrace.ast.exp.binary.rel.NEqExpNode;
import lang.dtrace.ast.exp.binary.sum.AddExpNode;
import lang.dtrace.ast.exp.binary.sum.SubExpNode;
import lang.dtrace.ast.exp.unary.log.NotExpNode;
import lang.dtrace.ast.id.ProbeIdNode;
import lang.dtrace.ast.id.ProbeSpecNode;
import lang.dtrace.ast.id.VarIdNode;
import lang.dtrace.ast.val.IntNode;
import lang.dtrace.ast.val.StringNode;

// TODO -- what should these methods return?
public interface DtraceASTVisitor {
    // Logical expressions
    public Number visitAndExp(AndExpNode andExpNode);
    public Number visitOrExp(OrExpNode orExpNode);

    // Relational expressions
    public Number visitEqExp(EqExpNode eqExpNode);
    public Number visitGTEqExp(GTEqExpNode gtEqExpNode);
    public Number visitGTExp(GTExpNode gtExpNode);
    public Number visitLTEqExp(LTEqExpNode ltEqExpNode);
    public Number visitLTExp(LTExpNode ltExpNode);
    public Number visitNEqExp(NEqExpNode nEqExpNode);

    // Multiplication Expressions
    public Number visitDivExp(DivExpNode divExpNode);
    public Number visitModExp(ModExpNode modExpNode);
    public Number visitMulExp(MulExpNode mulExpNode);

    // Sum Expressions
    public Number visitAddExp(AddExpNode addExpNode);
    public Number visitSubExp(SubExpNode subExpNode);

    // Unary Expressions
    public Number visitNotExp(NotExpNode notExpNode);

    // ID Nodes
    public Number visitProbeId(ProbeIdNode probeIdNode);
    public Number visitProbeSpec(ProbeSpecNode probeSpecNode);
    public Number visitVarId(VarIdNode varIdNode);

    // Basic Value Nodes
    public Number visitInt(IntNode intNode);
    public Number visitString(StringNode stringNode);
}
