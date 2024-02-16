package lang.dtrace;

import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.visitor.ASTBuilderVisitor;
import lang.dtrace.visitor.DtraceASTDumper;
import lang.dtrace.visitor.DtraceVisitor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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
//            "provider:module:function:name / i == (1 + 3) / { i }", // TODO -- fix

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
//            "provider:module:function:name / (1 + 3) / { i }", // TODO -- make invalid on type check

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

    @Test
    public void testASTDumper() throws IOException {
        String dscript = "provider:module:function:name / (i == \"1\") && (b == \"2\") / { i }";
        ParseUtils.ErrorListener errorListener = new ParseUtils.ErrorListener();
        DtraceParser.DscriptContext concreteParseTree = ParseUtils.parseDscript(dscript, errorListener);
        assertFalse(errorListener.isFail());

        // Create AST
        ASTBuilderVisitor astBuilder = new ASTBuilderVisitor();
        DtraceASTNode ast = concreteParseTree.accept(astBuilder); // knows type because of `ASTBuilderVisitor extends DtraceParserBaseVisitor<DtraceASTNode>`

        DtraceVisitor<String> dumper = new DtraceASTDumper();
        String result = ast.accept(dumper);
        logger.info(result);
    }

    private boolean isValidLanguageString(String languageString) throws IOException {
        ParseUtils.ErrorListener errorListener = new ParseUtils.ErrorListener();
        ParseUtils.parseDscript(languageString, errorListener);
        return !errorListener.isFail();
    }
}
