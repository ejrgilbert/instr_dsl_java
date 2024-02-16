package lang.dtrace.ast.id;

import lang.dtrace.visitor.DtraceVisitor;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ProbeId extends Id {
    private static final String VAR_REGEX = "([0-9]|[a-z]|[A-Z]|_|\\*|\\+|\\\\|\\?|\\!|\\[|\\])*";
    Pattern regex = Pattern.compile(VAR_REGEX);

    /**
     * Should only contain the following chars:
     * PROBE_SYM : 'A' .. 'Z' |
     *             'a' .. 'z' |
     *             '_' |
     *             '0' .. '9' |
     *             '*' |
     *             '+' |
     *             '\\' |
     *             '?' |
     *             '!' |
     *             '[' |
     *             ']' ;
     */
    public ProbeId(String id) {
        super();
        if (!regex.matcher(id).matches()) {
            throw new PatternSyntaxException("Var name '" + id + "' is not matched by pattern", VAR_REGEX, -1);
        }
        this.id = id;
    }

    @Override
    public <T> T accept(DtraceVisitor<? extends T> visitor) {
        return visitor.visitProbeId(this);
    }
}
