package lang.dtrace.ast;

import lang.dtrace.visitor.DtraceASTVisitor;

public abstract class DtraceASTNode {
    public abstract Object accept(DtraceASTVisitor visitor);
}
