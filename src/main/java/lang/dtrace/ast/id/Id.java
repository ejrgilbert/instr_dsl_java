package lang.dtrace.ast.id;

import lang.dtrace.ast.DtraceASTNode;

public abstract class Id implements DtraceASTNode {
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
