package lang.dtrace.ast.id;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.PatternSyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test the format of Vars allowed by the regex.
 */
public class VarIdTest {
    public static final Logger logger = LoggerFactory.getLogger(VarIdTest.class);

    public static final String[] validVarIds = {
            "a",
            "a_",
            "a1",
            "A123",
            "B1a",
            "B1_a123"
    };

    public static final String[] invalidVarIds = {
            "1",
            "_",
            "+",
            "1a",
            "_1A"
    };

    @Test
    public void testValidVarIds() throws Exception {
        for (String valid : validVarIds) {
            assertTrue("id = '" + valid + "' is not recognized as valid, but it should be", isValidVarId(valid));
        }
    }

    @Test
    public void testInvalidVarIds() throws IOException {
        for (String invalid : invalidVarIds) {
            assertFalse("id '" + invalid + "' is recognized as valid, but it should not", isValidVarId(invalid));
        }
    }

    private boolean isValidVarId(String varId) throws IOException {
        try {
            new VarId(varId);
            return true;
        } catch (PatternSyntaxException e) {
            logger.info(e.getMessage());
            return false;
        }
    }
}
