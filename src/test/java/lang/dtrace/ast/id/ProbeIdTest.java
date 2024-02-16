package lang.dtrace.ast.id;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.PatternSyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test the format of Probes allowed by the regex.
 */
public class ProbeIdTest {
    public static final Logger logger = LoggerFactory.getLogger(ProbeIdTest.class);


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
    public static final String[] validProbeIds = {
            "",
            "a",
            "aA",
            "a1",
            "0123",
            "___",
            "*",
            "+",
            "a*",
            "\\\\",
            "\\?![_ab120AZ*+]",
    };

    public static final String[] invalidProbeIds = {
            "-",
    };

    @Test
    public void testValidProbeIds() throws Exception {
        for (String valid : validProbeIds) {
            assertTrue("id = '" + valid + "' is not recognized as valid, but it should be", isValidProbeId(valid));
        }
    }

    @Test
    public void testInvalidProbeIds() throws IOException {
        for (String invalid : invalidProbeIds) {
            assertFalse("id '" + invalid + "' is recognized as valid, but it should not", isValidProbeId(invalid));
        }
    }

    private boolean isValidProbeId(String probeId) throws IOException {
        try {
            new ProbeId(probeId);
            return true;
        } catch (PatternSyntaxException e) {
            logger.info(e.getMessage());
            return false;
        }
    }
}
