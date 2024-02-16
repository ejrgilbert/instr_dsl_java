package lang.dtrace.ast.id;

import lang.dtrace.visitor.DtraceVisitor;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class VarId extends Id {
    private static final String VAR_REGEX = "([a-z]|[A-Z])+(_|[0-9]|[a-z]|[A-Z])*";
    Pattern regex = Pattern.compile(VAR_REGEX);
    public VarId(String id) throws PatternSyntaxException {
        super();
        if (!regex.matcher(id).matches()) {
            throw new PatternSyntaxException("Var name '" + id + "' is not matched by pattern", VAR_REGEX, -1);
        }
        this.id = id;
    }

    @Override
    public <T> T accept(DtraceVisitor<? extends T> visitor) {
        return visitor.visitVarId(this);
    }
}
