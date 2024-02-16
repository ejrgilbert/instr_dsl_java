package lang.dtrace.ast.val;

import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.visitor.DtraceVisitor;

public class Int implements DtraceASTNode {
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Int(Integer value) {
        super();
        setValue(value);
    }

    public Int(String value) {
        this(Integer.valueOf(value));
    }

    @Override
    public <T> T accept(DtraceVisitor<? extends T> visitor){ return visitor.visitInt(this); }
}
