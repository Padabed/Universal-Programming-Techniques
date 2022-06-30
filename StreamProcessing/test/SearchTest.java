import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class SearchTest {

    private static final Path DIRECTORY_1 = Path.of("");
    private static final Path DIRECTORY_2 = Path.of("data");

    private static final String NAME_SRC = "src";
    private static final String NAME_MAIN = "Main";
    private static final String NAME_MAIN_1 = "config/src/Main/";
    private static final String NAME_MAIN_1_2 = "config/Main/";
    private static final String NAME_FILE_TXT = "file.txt";
    private static final String NAME_FILE_TXT_2 = "config/file.txt";
    private static final String NAME_FILE_TXT_3 = "config/Main/file.txt";
    private static final String NAME_CONFIG_ZIP = "config/src/";
    private static final String NAME_BIG_FILE_TXT = "bigFile.txt";
    private static final String NAME_BIG_FILE_TXT_2 = "config/bigFile.txt";
    private static final String NAME_BIG_FILE_TXT_3 = "config/src/bigFile.txt";

    private static final String PATH_SRC = "src";
    private static final String PATH_SRC_2 = "config/src";
    private static final String PATH_FILE_TXT = "data/bigData/file.txt";
    private static final String PATH_CONFIG_JAR = "data/bigData/config.jar";
    private static final String PATH_CONFIG_ZIP = "data/bigData/config.zip";
    private static final String PATH_CONFIG_ZIP_2 = "config/src/";
    private static final String PATH_BIG_FILE_TXT = "data/bigData/bigFile.txt";

    private static final File FILE_SRC = new File(PATH_SRC);
    private static final File FILE_SRC_2 = new File(PATH_SRC_2);
    private static final File FILE_FILE_TXT = new File(PATH_FILE_TXT);
    private static final File FILE_BIG_FILE_TXT = new File(PATH_BIG_FILE_TXT);


    private static String CONTENT = getContent();

    private static String getContent() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(PATH_FILE_TXT)));
            StringBuilder sb = new StringBuilder();
            int next;
            while ((next = br.read()) != -1) {
                sb.append((char) next);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void findByNameInDirectory() {

        List<File> entries = InDirectorySearchUtility.findByName(DIRECTORY_1, NAME_SRC);

        System.out.println("--findByNameInDirectory--");
        System.out.println("The method found the following: " + entries + "\n");

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(FILE_SRC, entries.get(1));
        Assert.assertEquals(FILE_SRC_2, entries.get(0));
        Assert.assertEquals(NAME_SRC, entries.get(1).getName());
        Assert.assertEquals(NAME_SRC, entries.get(0).getName());
        Assert.assertEquals(PATH_SRC, entries.get(1).getPath());
        Assert.assertEquals(PATH_SRC_2, entries.get(0).getPath());

    }

    @Test
    public void findByContentInDirectory() {

        List<File> entries = InDirectorySearchUtility.findByContent(DIRECTORY_2, CONTENT);

        System.out.println("--findByContentInDirectory--");
        System.out.println("The method found the following: " + entries + "\n");

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_BIG_FILE_TXT, entries.get(1).getName());
        Assert.assertEquals(NAME_FILE_TXT, entries.get(0).getName());
        Assert.assertEquals(PATH_BIG_FILE_TXT, entries.get(1).getPath());
        Assert.assertEquals(PATH_FILE_TXT, entries.get(0).getPath());
        Assert.assertEquals(FILE_BIG_FILE_TXT, entries.get(1));
        Assert.assertEquals(FILE_FILE_TXT, entries.get(0));

    }

    @Test
    public void findByNameInDirectoryParallel() {

        List<File> entries = InDirectorySearchUtility.findByNameParallel(DIRECTORY_1, NAME_SRC);

        System.out.println("--findByNameInDirectoryParallel--");
        System.out.println("The method found the following: " + entries + "\n");

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_SRC, entries.get(0).getName());
        Assert.assertEquals(NAME_SRC, entries.get(1).getName());
        Assert.assertEquals(PATH_SRC, entries.get(1).getPath());
        Assert.assertEquals(PATH_SRC_2, entries.get(0).getPath());
        Assert.assertEquals(FILE_SRC, entries.get(1));
        Assert.assertEquals(FILE_SRC_2, entries.get(0));

    }

    @Test
    public void findByContentInDirectoryParallel() {

        List<File> entries = InDirectorySearchUtility.findByContentParallel(DIRECTORY_2, CONTENT);

        System.out.println("--findByContentInDirectoryParallel--");
        System.out.println("The method found the following: " + entries + "\n");

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_FILE_TXT, entries.get(0).getName());
        Assert.assertEquals(NAME_BIG_FILE_TXT, entries.get(1).getName());
        Assert.assertEquals(PATH_FILE_TXT, entries.get(0).getPath());
        Assert.assertEquals(PATH_BIG_FILE_TXT, entries.get(1).getPath());
        Assert.assertEquals(FILE_FILE_TXT, entries.get(0));
        Assert.assertEquals(FILE_BIG_FILE_TXT, entries.get(1));

    }

    @Test
    public void findByNameInZip() {

        ZipFile zip = null;
        try {
            zip = new ZipFile(PATH_CONFIG_ZIP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(zip);

        List<ZipEntry> entries = InZipSearchUtility.findByName(zip, NAME_SRC);

        System.out.println("--findByContentInDirectoryParallel--");
        System.out.println("The method found the following: " + entries + "\n");

        Assert.assertEquals(1, entries.size());
        Assert.assertEquals(NAME_CONFIG_ZIP, entries.get(0).getName());
        Assert.assertEquals(PATH_CONFIG_ZIP_2, entries.get(0).getName());

        entries = InZipSearchUtility.findByName(zip, NAME_MAIN);

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_MAIN_1, entries.get(1).getName());
        Assert.assertEquals(NAME_MAIN_1_2, entries.get(0).getName());

    }

    @Test
    public void findByContentInZip() {

        ZipFile zip = null;
        try {
            zip = new ZipFile(PATH_CONFIG_ZIP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(zip);

        List<ZipEntry> entries = InZipSearchUtility.findByContent(zip, CONTENT);

        System.out.println("--findByContentInZip--");
        System.out.println("The method found the following: " + entries + "\n");

        Assert.assertEquals(4, entries.size());
        Assert.assertEquals(NAME_FILE_TXT_2, entries.get(0).getName());
        Assert.assertEquals(NAME_BIG_FILE_TXT_2, entries.get(1).getName());
        Assert.assertEquals(NAME_FILE_TXT_3, entries.get(2).getName());
        Assert.assertEquals(NAME_BIG_FILE_TXT_3, entries.get(3).getName());

    }

    @Test
    public void findByNameInZipParallel() {

        ZipFile zip = null;
        try {
            zip = new ZipFile(PATH_CONFIG_ZIP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(zip);

        List<ZipEntry> entries = InZipSearchUtility.findByNameParallel(zip, NAME_MAIN);

        System.out.println("--findByNameInZipParallel--");
        System.out.println("The method found the following: " + entries + "\n");

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_MAIN_1, entries.get(1).getName());
        Assert.assertEquals(NAME_MAIN_1_2, entries.get(0).getName());

    }

    @Test
    public void findByContentInZipParallel() {

        ZipFile zip = null;
        try {
            zip = new ZipFile(PATH_CONFIG_ZIP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(zip);

        List<ZipEntry> entries = InZipSearchUtility.findByContentParallel(zip, CONTENT);

        System.out.println("--findByContentInZipParallel--");
        System.out.println("The method found the following: " + entries + "\n");

        Assert.assertEquals(4, entries.size());
        Assert.assertEquals(NAME_FILE_TXT_2, entries.get(0).getName());
        Assert.assertEquals(NAME_BIG_FILE_TXT_2, entries.get(1).getName());
        Assert.assertEquals(NAME_FILE_TXT_3, entries.get(2).getName());
        Assert.assertEquals(NAME_BIG_FILE_TXT_3, entries.get(3).getName());

    }

    @Test
    public void findByNameInJar() {

        JarFile jar = null;
        try {
            jar = new JarFile(PATH_CONFIG_JAR);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(jar);

        List<JarEntry> entries = InJarSearchUtility.findByName(jar, NAME_MAIN);

        System.out.println("--findByNameInJar--");
        System.out.println("The method found the following: " + entries + "\n");

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_MAIN_1, entries.get(1).getName());
        Assert.assertEquals(NAME_MAIN_1_2, entries.get(0).getName());

    }

    @Test
    public void findByContentInJar() {
        JarFile jar = null;
        try {
            jar = new JarFile(PATH_CONFIG_JAR);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(jar);

        List<JarEntry> entries = InJarSearchUtility.findByContent(jar, CONTENT);

        System.out.println("--findByContentInJar--");
        System.out.println("The method found the following: " + entries + "\n");

        Assert.assertEquals(4, entries.size());
        Assert.assertEquals(NAME_FILE_TXT_2, entries.get(0).getName());
        Assert.assertEquals(NAME_BIG_FILE_TXT_2, entries.get(1).getName());
        Assert.assertEquals(NAME_FILE_TXT_3, entries.get(2).getName());
        Assert.assertEquals(NAME_BIG_FILE_TXT_3, entries.get(3).getName());

    }

    @Test
    public void findByNameInJarParallel() {

        JarFile jar = null;
        try {
            jar = new JarFile(PATH_CONFIG_JAR);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(jar);

        List<JarEntry> entries = InJarSearchUtility.findByNameParallel(jar, NAME_MAIN);

        System.out.println("--findByNameInJarParallel--");
        System.out.println("The method found the following: " + entries + "\n");

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_MAIN_1, entries.get(1).getName());
        Assert.assertEquals(NAME_MAIN_1_2, entries.get(0).getName());

    }

    @Test
    public void findByContentInJarParallel() {

        JarFile jar = null;
        try {
            jar = new JarFile(PATH_CONFIG_JAR);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(jar);

        List<JarEntry> entries = InJarSearchUtility.findByContentParallel(jar, CONTENT);

        System.out.println("--findByContentInJarParallel--");
        System.out.println("The method found the following: " + entries + "\n");

        Assert.assertEquals(4, entries.size());
        Assert.assertEquals(NAME_FILE_TXT_2, entries.get(0).getName());
        Assert.assertEquals(NAME_BIG_FILE_TXT_2, entries.get(1).getName());
        Assert.assertEquals(NAME_FILE_TXT_3, entries.get(2).getName());
        Assert.assertEquals(NAME_BIG_FILE_TXT_3, entries.get(3).getName());

    }

}
