package lang.dtrace;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;

public class ParseUtils {

    public static DtraceParser.DscriptContext parseDscript(String script, ANTLRErrorListener errorListener) throws IOException {
        try (ByteArrayInputStream is = new ByteArrayInputStream(script.getBytes(StandardCharsets.UTF_8))) {
            return parseDscript(is, errorListener);
        }
    }

    public static DtraceParser.DscriptContext parseDscript(File script, ANTLRErrorListener errorListener) throws IOException {
        try (InputStream is = new FileInputStream(script)) {
            return parseDscript(is, errorListener);
        }
    }

    public static DtraceParser.DscriptContext parseDscript(InputStream is, ANTLRErrorListener errorListener) throws IOException {
        if (errorListener == null) {
            errorListener = new ErrorListener();
        }

        CharStream charStream;
        try (InputStreamReader isr = new InputStreamReader(is, Charset.defaultCharset())) {
            charStream = CharStreams.fromReader(isr);
        }

        DtraceLexer lexer = new DtraceLexer(charStream);
        lexer.addErrorListener(errorListener);
        TokenStream inputTokenStream = new CommonTokenStream(lexer);

        DtraceParser parser = new DtraceParser(inputTokenStream);

        parser.addErrorListener(errorListener);
        return parser.dscript();
    }

    public static class ErrorListener extends BaseErrorListener {
        private boolean fail = false;

        public boolean isFail() {
            return fail;
        }

        public void setFail(boolean fail) {
            this.fail = fail;
        }

        @Override
        public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2,
                                int arg3, String arg4, RecognitionException arg5) {
            setFail(true);
        }

        @Override
        public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2,
                                             int arg3, int arg4, ATNConfigSet arg5) {
            setFail(true);
        }

        @Override
        public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2,
                                                int arg3, BitSet arg4, ATNConfigSet arg5) {
            setFail(true);
        }

        @Override
        public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3,
                                    boolean arg4, BitSet arg5, ATNConfigSet arg6) {
            setFail(true);
        }
    }
}