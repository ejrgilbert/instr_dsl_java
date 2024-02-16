package lang.dtrace.visitor;

import lang.dtrace.ast.DScript;
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
import lang.dtrace.ast.probe.Probe;
import lang.dtrace.ast.probe.ProbeSpec;
import lang.dtrace.ast.id.VarId;
import lang.dtrace.ast.stmt.Stmt;
import lang.dtrace.ast.val.Int;
import lang.dtrace.ast.val.Str;

public interface DtraceVisitor<T> {
    public T visitDScript(DScript visitor);

    // Logical expressions
    public T visitAndExp(AndExp andExpNode);
    public T visitOrExp(OrExp orExpNode);

    // Relational expressions
    public T visitEqExp(EqExp eqExpNode);
    public T visitGTEqExp(GTEqExp gtEqExpNode);
    public T visitGTExp(GTExp gtExpNode);
    public T visitLTEqExp(LTEqExp ltEqExpNode);
    public T visitLTExp(LTExp ltExpNode);
    public T visitNEqExp(NEqExp nEqExpNode);

    // Multiplication Expressions
    public T visitDivExp(DivExp divExpNode);
    public T visitModExp(ModExp modExpNode);
    public T visitMulExp(MulExp mulExpNode);

    // Sum Expressions
    public T visitAddExp(AddExp addExpNode);
    public T visitSubExp(SubExp subExpNode);

    // Unary Expressions
    public T visitNotExp(NotExp notExpNode);

    // Statements
    public T visitStmt(Stmt stmtNode);

    // ID Nodes
    public T visitVarId(VarId varIdNode);
    public T visitProbeId(ProbeId probeIdNode);

    // Probe Nodes
    public T visitProbe(Probe probeNode);
    public T visitProbeSpec(ProbeSpec probeSpecNode);

    // Basic Value Nodes
    public T visitInt(Int intNode);
    public T visitString(Str stringNode);
}
