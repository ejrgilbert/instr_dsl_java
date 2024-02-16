package lang.dtrace.ast.exp.unary;

import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.ast.exp.Exp;

public abstract class UnExp extends Exp {
    public DtraceASTNode operand;

    public DtraceASTNode getOperand() {
        return operand;
    }

    public void setOperand(DtraceASTNode operand) {
        this.operand = operand;
    }
}
