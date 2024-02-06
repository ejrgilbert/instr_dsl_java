package lang.dtrace.ast.exp.binary.log;

import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.ast.exp.binary.BinExpNode;
import lang.dtrace.visitor.DtraceASTVisitor;

public class OrExpNode extends BinExpNode {
    public OrExpNode(DtraceASTNode leftOperand, DtraceASTNode rightOperand) {
        super();
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public Object accept(DtraceASTVisitor visitor) {
        return visitor.visitOrExp(this);
    }
}
