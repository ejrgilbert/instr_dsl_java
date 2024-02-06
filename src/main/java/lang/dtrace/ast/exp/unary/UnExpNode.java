package lang.dtrace.ast.exp.unary;

import lang.dtrace.ast.DtraceASTNode;

public abstract class UnExpNode extends DtraceASTNode {
    public DtraceASTNode operand;

    public DtraceASTNode getOperand() {
        return operand;
    }

    public void setOperand(DtraceASTNode operand) {
        this.operand = operand;
    }
}
