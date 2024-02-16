package lang.dtrace.ast.exp.unary.log;

import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.ast.exp.unary.UnExp;
import lang.dtrace.visitor.DtraceVisitor;

public class NotExp extends UnExp {
    public NotExp(DtraceASTNode operand) {
        super();
        this.operand = operand;
    }

    @Override
    public <T> T accept(DtraceVisitor<? extends T> visitor) {
        return visitor.visitNotExp(this);
    }
}
