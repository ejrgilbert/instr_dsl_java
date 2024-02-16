package lang.dtrace.ast.exp.binary.sum;

import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.ast.exp.binary.BinExp;
import lang.dtrace.visitor.DtraceVisitor;

public class AddExp extends BinExp {
    public AddExp(DtraceASTNode leftOperand, DtraceASTNode rightOperand) {
        super();
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public <T> T accept(DtraceVisitor<? extends T> visitor) {
        return visitor.visitAddExp(this);
    }
}
