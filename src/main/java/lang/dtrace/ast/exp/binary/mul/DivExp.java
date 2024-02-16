package lang.dtrace.ast.exp.binary.mul;

import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.ast.exp.binary.BinExp;
import lang.dtrace.visitor.DtraceVisitor;

public class DivExp extends BinExp {
    public DivExp(DtraceASTNode leftOperand, DtraceASTNode rightOperand) {
        super();
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public <T> T accept(DtraceVisitor<? extends T> visitor) {
        return visitor.visitDivExp(this);
    }
}
