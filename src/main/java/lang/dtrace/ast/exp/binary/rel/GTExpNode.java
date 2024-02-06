package lang.dtrace.ast.exp.binary.rel;

import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.ast.exp.binary.BinExpNode;
import lang.dtrace.visitor.DtraceASTVisitor;

public class GTExpNode extends BinExpNode {
    public GTExpNode(DtraceASTNode leftOperand, DtraceASTNode rightOperand) {
        super();
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public Object accept(DtraceASTVisitor visitor) {
        return visitor.visitGTExp(this);
    }
}
