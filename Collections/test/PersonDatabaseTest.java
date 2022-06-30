import main.InputParser;
import main.Person;
import main.PersonDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class PersonDatabaseTest {

    private PersonDatabase pd;

    @Before
    public void init() throws FileNotFoundException {
        pd = new PersonDatabase();
    }

    @Test
    public void sortedByFirstName() {
        Assert.assertEquals(List.of("Andrei", "Anton", "Anton", "Mark", "Marker", "Nikita"),
                pd.sortedByFirstName().stream().map(Person::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void sortedBySurnameFirstNameAndBirthdate() {
        Assert.assertEquals(List.of("Genadevich", "Sergeev", "Genadevich", "Ladyshev", "Kagasian", "Padabed"),
                pd.sortedBySurnameFirstNameAndBirthdate().stream().map(Person::getSurname).collect(Collectors.toList()));
    }

    @Test
    public void sortedByBirthdate() {
        Assert.assertEquals(List.of("Andrei", "Anton", "Anton", "Marker", "Mark", "Nikita"),
                pd.sortedByBirthdate().stream().map(Person::getFirstName).collect(Collectors.toList()));
    }

    @Test
    public void bornOnDay() throws ParseException {
        Assert.assertEquals(List.of("Nikita"),
                pd.bornOnDay(InputParser.DATEFORMAT.parse("2003-01-24")).stream().map(Person::getFirstName).collect(Collectors.toList())
        );
    }

}