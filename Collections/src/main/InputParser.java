package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public final class InputParser {

    // 1. Use regular expresssions (Pattern) for validating input data
    //    U¿yæ regularnych wyra¿eñ (Pattern) do walidacji danych wejœciowych
    //
    // 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd"
    //    Konwersjê wejœciowego ci¹gu znaków reprezentuj¹cego datê nale¿y oprzeæ np. DateFormat
    //    SimpleDateFormat format "yyyy-MM-dd"

    public static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final String GENERAL_REGEX = "[A-Z][a-z]+ [A-Z][a-z]+ [1-2][0-9]{3}-[0-1][1-9]-[0-3][0-9]";

    public static List<Person> parse(File file) {

        List<Person> pers = new LinkedList<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] lines;
                if (line.matches(GENERAL_REGEX)) {
                    lines = line.split("\\s+");
                    try {
                        Person p = new Person(lines[0], lines[1], DATEFORMAT.parse(lines[2]));
                        pers.add(p);
                    } catch (Exception ignore) {}
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return pers;
    }
}
