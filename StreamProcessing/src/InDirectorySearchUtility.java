import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InDirectorySearchUtility {

    private static Predicate<Path> isRegularFile = Files::isRegularFile;
    private static Predicate<Path> notArchive = p -> ExtensionCheckerUtility.notArchive(p.toString());

    private InDirectorySearchUtility() {
    }

    private static boolean hasTheName(Path p, String name) {
        return p.getFileName().toString().equals(name);
    }

    private static boolean hasTheContent(Path p, String content) {
        File file = p.toFile();
        try (InputStream in = new FileInputStream(file)) {
            return ContentSearchUtility.containsText(in, (int) file.length(), content);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<File> findByName(Path path, String name) {
        Predicate<Path> hasTheName = p -> hasTheName(p, name);
        List<File> entries = null;
        try {
            entries = Files.walk(path).
                    filter(hasTheName).
                    map(p -> new File(p.toString())).
                    collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public static List<File> findByContent(Path path, String content) {
        Predicate<Path> hasTheContent = p -> hasTheContent(p, content);
        List<File> entries = null;
        try {
            entries = Files.walk(path).
                    filter(isRegularFile.and(notArchive).and(hasTheContent)).
                    map(p -> new File(p.toString())).
                    collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public static List<File> findByNameParallel(Path path, String name) {
        Predicate<Path> hasTheName = p -> hasTheName(p, name);
        List<File> entries = null;
        try {
            entries = Files.walk(path).
                    parallel().
                    filter(hasTheName).
                    map(p -> new File(p.toString())).
                    collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public static List<File> findByContentParallel(Path path, String content) {
        Predicate<Path> hasTheContent = p -> hasTheContent(p, content);
        List<File> entries = null;
        try {
            entries = Files.walk(path).
                    parallel().
                    filter(isRegularFile.and(notArchive).and(hasTheContent)).
                    map(p -> new File(p.toString())).
                    collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

}
