import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class InJarSearchUtility {

    private static Predicate<JarEntry> isDirectory = JarEntry::isDirectory;
    private static Predicate<JarEntry> notArchive = entry -> ExtensionCheckerUtility.notArchive(entry.toString());

    private InJarSearchUtility() {
    }

    private static boolean hasTheName(JarEntry entry, String name) {
        return new File(entry.getName()).getName().equals(name);
    }

    private static boolean hasTheContent(JarFile archive, JarEntry entry, String content) {
        try (InputStream in = new BufferedInputStream(archive.getInputStream(entry))) {
            return ContentSearchUtility.containsText(in, (int) entry.getSize(), content);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<JarEntry> findByName(JarFile archive, String name) {
        Predicate<JarEntry> hasTheName = entry -> hasTheName(entry, name);
        return archive.stream().
                filter(hasTheName).
                collect(Collectors.toList());
    }

    public static List<JarEntry> findByContent(JarFile archive, String content) {
        Predicate<JarEntry> hasTheContent = entry -> hasTheContent(archive, entry, content);
        return archive.stream().
                filter(isDirectory.negate().and(notArchive).and(hasTheContent)).
                collect(Collectors.toList());
    }

    public static List<JarEntry> findByNameParallel(JarFile archive, String name) {
        Predicate<JarEntry> hasTheName = entry -> hasTheName(entry, name);
        return archive.stream().
                parallel().
                filter(hasTheName).
                collect(Collectors.toList());
    }

    public static List<JarEntry> findByContentParallel(JarFile archive, String content) {
        Predicate<JarEntry> hasTheContent = entry -> hasTheContent(archive, entry, content);
        return archive.stream().
                parallel().
                filter(isDirectory.negate().and(notArchive).and(hasTheContent)).
                collect(Collectors.toList());
    }

}
