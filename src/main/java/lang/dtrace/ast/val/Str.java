package lang.dtrace.ast.val;

import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.visitor.DtraceVisitor;

public class Str implements DtraceASTNode {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Str(String value) {
        super();
        setValue(value);
    }

    @Override
    public <T> T accept(DtraceVisitor<? extends T> visitor){ return visitor.visitString(this); }
}
