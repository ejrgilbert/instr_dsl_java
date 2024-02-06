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
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DtraceParserTest {
    public static final Logger logger = LoggerFactory.getLogger(DtraceParserTest.class);

    public static final String[] validStrings = {
            // Variations of PROBE_SPEC
            "provider:module:function:name { }",
            "prov*:module:function { }",
            "provider:module { }",
            "provider { }",
            "::: { }",
            "provider::: { }",
            ":module:: { }",
            "::function: { }",
            ":::name { }",
            ":module:function:name { }",
            "provider::function:name { }",
            "provider:module::name { }",

            // Predicates
            "provider:module:function:name / i / { }",
            "provider:module:function:name / \"i\" <= 1 / { }",
            "provider:module:function:name / i54 < r77 / { }",
            "provider:module:function:name / i54 < r77 / { }",
            "provider:module:function:name / i != 7 / { }",
            "provider:module:function:name / (i == \"1\") && (b == \"2\") / { }",
            "provider:module:function:name / i == \"1\" && b == \"2\" / { }",

            // Statements
            """
            provider:module:function:name {
                i
            }
            """,

            // Comments
            "provider:module:function:name { } /* */",
            "/* comment */\nprovider:module:function:name { } /* */",
            """
            provider:module:function:name {
                i /* Comment about statement */
            }
            """,
    };

    public static final String[] invalidStrings = {
            // Variations of PROBE_SPEC
            "provider:module:function:name: { }",
            "provider:module:function:name",

            // Empty predicate
            "provider:module:function:name // { }",
            "provider:module:function:name / 5i < r77 / { }",
//            "provider:module:function:name / i < 1 < 2 / { }", // TODO -- make invalid on semantic pass

            // bad statement
            "provider:module:function:name / i == 1 / { 2i }",
    };

    @Test
    public void testValidStrings() throws Exception {
        for (String valid : validStrings) {
            assertTrue("string = '" + valid + "' is not recognized as valid, but it should be", isValidLanguageString(valid));
        }
    }

    @Test
    public void testInvalidStrings() throws IOException {
        for (String invalid : invalidStrings) {
            assertFalse("string '" + invalid + "' is recognized as valid, but it should not", isValidLanguageString(invalid));
        }
    }

    private boolean isValidLanguageString(String languageString) throws IOException {
        ParseUtils.ErrorListener errorListener = new ParseUtils.ErrorListener();
        ParseUtils.parseDscript(languageString, errorListener);
        return !errorListener.isFail();
    }
}
