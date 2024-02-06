package lang.dtrace;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;

/**
 * This test attempts to parse all *.d files in the test `resources/dscripts` folder.
 */
public class DScriptTest {
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
    public void wizardMonitors() {

    }

    @Test
    public void replay() {

    }

    private File[] getTestFiles(String subdir) {
        File dir = new File(SCRIPT_DIR + subdir);
        return dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".d"));
    }
}
