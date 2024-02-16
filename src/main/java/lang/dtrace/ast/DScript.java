package lang.dtrace.ast;

import lang.dtrace.ast.probe.Probe;
import lang.dtrace.visitor.DtraceVisitor;

import java.util.ArrayList;
import java.util.List;

public class DScript implements DtraceASTNode {
    public List<DtraceASTNode> probes;

    public List<DtraceASTNode> getProbes() {
        return probes;
    }

    public void setProbes(List<DtraceASTNode> probes) {
        this.probes = probes;
    }

    public void addProbe(DtraceASTNode probe) {
        probes.add(probe);
    }
    public DScript(List<DtraceASTNode> probes) {
        super();
        this.probes = probes;
    }

    public DScript() {
        this(new ArrayList<>());
    }

    @Override
    public <T> T accept(DtraceVisitor<? extends T> visitor) {
        return visitor.visitDScript(this);
    }
}
