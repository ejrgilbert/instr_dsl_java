package lang.dtrace.ast.exp.unary.log;

import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.ast.exp.unary.UnExpNode;
import lang.dtrace.visitor.DtraceASTVisitor;

public class NotExpNode extends UnExpNode {
    public NotExpNode(DtraceASTNode operand) {
        super();
        this.operand = operand;
    }

    @Override
    public Object accept(DtraceASTVisitor visitor) {
        return visitor.visitNotExp(this);
    }
}
