package lang.dtrace.ast.exp.binary.log;

import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.ast.exp.binary.BinExp;
import lang.dtrace.visitor.DtraceVisitor;

public class AndExp extends BinExp {
    public AndExp(DtraceASTNode leftOperand, DtraceASTNode rightOperand) {
        super();
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public <T> T accept(DtraceVisitor<? extends T> visitor) {
        return visitor.visitAndExp(this);
    }
}
