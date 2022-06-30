import main.InputParser;
import main.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class InputParserTest {

    private List<Person> list;
    private List<String> testSurnameList;
    private List<String> testFirstNameList;

    @Before
    public void init() {
        list = InputParser.parse(new File("src//PeopleInfo.txt"));
        testSurnameList = List.of("Padabed", "Kagasian", "Sergeev", "Genadevich", "Genadevich", "Ladyshev");
        testFirstNameList = List.of("Nikita", "Marker", "Anton", "Andrei", "Anton", "Mark");
    }

    @Test
    public void parse() {
        Assert.assertEquals(testSurnameList,
                list.stream().map(Person::getSurname).collect(Collectors.toList()));
        Assert.assertEquals(testFirstNameList,
                list.stream().map(Person::getFirstName).collect(Collectors.toList()));
    }

}
