import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class ContentSearchUtility {

    private ContentSearchUtility() {
    }

    public static boolean containsText(InputStream input, int length, String content) {
        Scanner scanner = new Scanner(input);
        String result = scanner.findWithinHorizon(content, length);
        return result != null;
    }

    public void toString(List entities){
        for(Object i: entities){
            System.out.println(i);
        }
    }

}
