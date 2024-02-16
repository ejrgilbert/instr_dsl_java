package lang.dtrace.error;

public class DtraceParseException extends IllegalArgumentException {
    public DtraceParseException(String msg) {
        super(msg);
    }
}
