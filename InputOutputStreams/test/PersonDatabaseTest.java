import main.InputParser;
import main.Person;
import main.PersonDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class PersonDatabaseTest {

    private final File txtFile = new File("src//data//input.txt");
    private final File binaryFile = new File("src//data//output.txt");

    private PersonDatabase pd;

    @Before
    public void init() throws FileNotFoundException {
        pd = new PersonDatabase();
    }

    @Test
    public void sortedByFirstName() {
        Assert.assertEquals(List.of("Andrei", "Anton", "Anton", "Mark", "Marker", "Nikita"),
                pd.sortedByFirstName()
                        .stream()
                        .map(Person::getFirstName)
                        .collect(Collectors.toList()));
    }

    @Test
    public void sortedBySurnameFirstNameAndBirthdate() {
        Assert.assertEquals(List.of("Genadevich", "Sergeev", "Genadevich", "Ladyshev", "Kagasian", "Padabed"),
                pd.sortedBySurnameFirstNameAndBirthdate()
                        .stream()
                        .map(Person::getSurname)
                        .collect(Collectors.toList()));
    }

    @Test
    public void sortedByBirthdate() {
        Assert.assertEquals(List.of("Andrei", "Anton", "Anton", "Marker", "Mark", "Nikita"),
                pd.sortedByBirthdate()
                        .stream()
                        .map(Person::getFirstName)
                        .collect(Collectors.toList()));
    }

    @Test
    public void bornOnDay() throws ParseException {
        Assert.assertEquals(List.of("Nikita"),
                pd.bornOnDay(InputParser.DATEFORMAT.parse("2003-01-24"))
                        .stream()
                        .map(Person::getFirstName)
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void serializeAndDeserialize() throws Throwable {

        InputParser parser = new InputParser();
        List<Person> persons = parser.parse(txtFile);
        PersonDatabase database = new PersonDatabase(persons);

        OutputStream output = new FileOutputStream(binaryFile);
        DataOutputStream dataOutput = new DataOutputStream(output);
        database.serialize(dataOutput);
        dataOutput.close();

        InputStream input = new FileInputStream(binaryFile);
        DataInputStream dataInput = new DataInputStream(input);
        PersonDatabase deserialized = PersonDatabase.deserialize(dataInput);
        dataInput.close();

        Assert.assertNotNull(String.valueOf(database), deserialized);
        Assert.assertEquals(database.getList().size(), deserialized.getList().size());
        System.out.println(database.toString());
        System.out.println(deserialized.toString());

    }

}

