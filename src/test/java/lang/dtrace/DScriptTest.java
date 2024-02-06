package lang.dtrace;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;

/**
 * This test attempts to parse all *.d files in the test `resources/dscripts` folder.
 */
public class DScriptTest {
    public static final Logger logger = LoggerFactory.getLogger(DtraceParserTest.class);
    private static final String SCRIPT_DIR = "src/test/resources/dscripts/";
    @Test
    public void faultInjection() throws IOException {
        File[] testFiles = getTestFiles("fault_injection");

        for (File f : testFiles) {
            ParseUtils.ErrorListener errorListener = new ParseUtils.ErrorListener();
            ParseUtils.parseDscript(f, errorListener);
            assertFalse(errorListener.isFail());
        }
    }

    @Test
    public void wizardMonitors() throws IOException {
        File[] testFiles = getTestFiles("wizard_monitors");
        if (testFiles.length == 0) {
            logger.error("No test scripts found for `wizard_monitors` test.");
        }

        for (File f : testFiles) {
            ParseUtils.ErrorListener errorListener = new ParseUtils.ErrorListener();
            ParseUtils.parseDscript(f, errorListener);
            assertFalse(errorListener.isFail());
        }
    }

    @Test
    public void replay() throws IOException {
        File[] testFiles = getTestFiles("replay");
        if (testFiles.length == 0) {
            logger.error("No test scripts found for `replay` test.");
        }

        for (File f : testFiles) {
            ParseUtils.ErrorListener errorListener = new ParseUtils.ErrorListener();
            ParseUtils.parseDscript(f, errorListener);
            assertFalse(errorListener.isFail());
        }
    }

    private File[] getTestFiles(String subdir) {
        File dir = new File(SCRIPT_DIR + subdir);
        return dir.listFiles((dir1, name) -> {
            if (name.toLowerCase().endsWith(".todo")) {
                logger.error("Test script labeled as TODO: " + name);
                return false;
            }
            return name.toLowerCase().endsWith(".d");
        });
    }
}
