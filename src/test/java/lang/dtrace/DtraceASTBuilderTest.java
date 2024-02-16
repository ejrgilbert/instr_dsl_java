package lang.dtrace;

import lang.dtrace.ast.DtraceASTNode;
import lang.dtrace.visitor.ASTBuilderVisitor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class DtraceASTBuilderTest {
    private static final Logger logger = LoggerFactory.getLogger(DtraceASTBuilderTest.class);

    public static final String[] validScripts = {
            // Variations of PROBE_SPEC
            "provider:module:function:name { }",
            ":module:function:name { }",
            "::function:name { }",
            ":::name { }",
            "::: { }",
            "name { }",
            "function:name { }",
            "module:function:name { }",
            "provider::function:name { }",
    };

    @Test
    public void testEvaluation() {
        int index = 0;
        for(String program : validScripts){
            try {
                testString(program);
            } catch(Exception ex) {
                fail("failed to build AST from \"" + program + "\"");
            }

            index += 1;
        }
    }

    public void testString(String program) throws IOException
    {
        ParseUtils.ErrorListener errorListener = new ParseUtils.ErrorListener();
        DtraceParser.DscriptContext concreteParseTree = ParseUtils.parseDscript(program, errorListener);
        assertFalse(errorListener.isFail());

        ASTBuilderVisitor visitor = new ASTBuilderVisitor();
        DtraceASTNode ast = concreteParseTree.accept(visitor); // knows type because of `ASTBuilderVisitor extends DtraceParserBaseVisitor<DtraceASTNode>`

        // TODO -- check that ast is valid? maybe...
//        ArithmeticASTVisitor astVisitor = new EvaluationVisitor();
//        Number evaluationResult = (Number)ast.accept(astVisitor);
    }
}
