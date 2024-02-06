package lang.dtrace.ast.exp.binary.mul;

import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.ast.exp.binary.BinExpNode;
import lang.dtrace.visitor.DtraceASTVisitor;

public class ModExpNode extends BinExpNode {
    public ModExpNode(DtraceASTNode leftOperand, DtraceASTNode rightOperand) {
        super();
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public Object accept(DtraceASTVisitor visitor) {
        return visitor.visitModExp(this);
    }
}
