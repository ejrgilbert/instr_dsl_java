package lang.dtrace.ast.probe;

import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.ast.id.ProbeId;
import lang.dtrace.error.DtraceParseException;
import lang.dtrace.visitor.DtraceVisitor;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class ProbeSpec implements DtraceASTNode {
    private static final String COLON = ":";
    private static final String STAR = "*";
    private static final int MAX_PARTS = 4;
    public ProbeId provider;
    public ProbeId module;
    public ProbeId function;
    public ProbeId name;

    public ProbeId getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        if (provider.isEmpty()) {
            this.provider = new ProbeId(STAR);
        } else {
            this.provider = new ProbeId(provider);
        }
    }

    public ProbeId getModule() {
        return module;
    }

    public void setModule(String module) {
        if (module.isEmpty()) {
            this.module = new ProbeId(STAR);
        } else {
            this.module = new ProbeId(module);
        }
    }

    public ProbeId getFunction() {
        return function;
    }

    public void setFunction(String function) {
        if (function.isEmpty()) {
            this.function = new ProbeId(STAR);
        } else {
            this.function = new ProbeId(function);
        }
    }

    public ProbeId getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            this.name = new ProbeId(STAR);
        } else {
            this.name = new ProbeId(name);
        }
    }

    public ProbeSpec(String spec) {
        String[] parts = spec.split(COLON);
        if (parts.length > MAX_PARTS) {
            throw new DtraceParseException("Probe specification has too many subparts, max " + MAX_PARTS+ ": " + spec);
        }

        ArrayList<String> padding = new ArrayList<>();
        for (int i = 0; i < MAX_PARTS - parts.length; i++) {
            padding.add(STAR);
        }

        padding.addAll(List.of(parts));

        setProvider(padding.get(0));
        setModule(padding.get(1));
        setFunction(padding.get(2));
        setName(padding.get(3));
    }

    @Override
    public <T> T accept(DtraceVisitor<? extends T> visitor) {
        return visitor.visitProbeSpec(this);
    }
}
