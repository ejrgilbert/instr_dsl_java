package lang.dtrace.ast.probe;

import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.ast.exp.Exp;
import lang.dtrace.ast.stmt.Stmt;
import lang.dtrace.visitor.DtraceVisitor;

import java.util.ArrayList;
import java.util.List;

public class Probe implements DtraceASTNode {
    public DtraceASTNode spec; // Should be ProbeSpec
    public DtraceASTNode predicate; // Should be Exp
    public List<DtraceASTNode> body; // Should be List<Stmt>

    public DtraceASTNode getSpec() {
        return spec;
    }

    public void setSpec(DtraceASTNode spec) {
        this.spec = spec;
    }

    public DtraceASTNode getPredicate() {
        return predicate;
    }

    public void setPredicate(DtraceASTNode predicate) {
        this.predicate = predicate;
    }

    public List<DtraceASTNode> getBody() {
        return body;
    }

    public void setBody(List<DtraceASTNode> body) {
        this.body = body;
    }
    public void addStmtToBody(DtraceASTNode stmt) {
        body.add(stmt);
    }

    public Probe() {
        body = new ArrayList<>();
    }

    @Override
    public <T> T accept(DtraceVisitor<? extends T> visitor) {
        return visitor.visitProbe(this);
    }
}
