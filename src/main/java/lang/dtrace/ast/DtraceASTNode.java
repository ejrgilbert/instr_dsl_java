package lang.dtrace.ast;

import lang.dtrace.visitor.DtraceVisitor;

public interface DtraceASTNode {
    <T> T accept(DtraceVisitor<? extends T> visitor);
}
