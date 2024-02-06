package lang.dtrace.ast.exp.binary;

import lang.dtrace.ast.DtraceASTNode;

public abstract class BinExpNode extends DtraceASTNode {
    public DtraceASTNode leftOperand;
    public DtraceASTNode rightOperand;

    public DtraceASTNode getLeftOperand() {
        return leftOperand;
    }

    public void setLeftOperand(DtraceASTNode leftOperand) {
        this.leftOperand = leftOperand;
    }

    public DtraceASTNode getRightOperand() {
        return rightOperand;
    }

    public void setRightOperand(DtraceASTNode rightOperand) {
        this.rightOperand = rightOperand;
    }
}
