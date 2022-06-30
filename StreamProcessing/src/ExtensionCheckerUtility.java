import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ExtensionCheckerUtility {

    private static final List<String> ARCHIVE_EXTENSIONS = new LinkedList<>(Arrays.asList(".zip", ".tar", ".jar", ".rar", ".7z"));

    private ExtensionCheckerUtility() {
    }

    public static boolean notArchive(String entry) {
        for (String extension : ARCHIVE_EXTENSIONS)
            if (entry.endsWith(extension)) return false;
        return true;
    }

}
