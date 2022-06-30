package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Person implements Comparable<Person> {

    private final String firstName;
    private final String surname;
    private final Date birthdate;

    public Person(String firstName, String surname, Date birthdate) {
        this.firstName = firstName;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public void serialize(DataOutputStream output) throws IOException {
        try {
            output.writeUTF(firstName);
            output.writeUTF(surname);
            DateUtility.serialize(birthdate, output);
        } catch (Throwable exception){
            throw new IOException(exception);
        }
    }

    public static Person deserialize(DataInputStream input) throws IOException {
        try {
            String firstname = input.readUTF();
            String surname = input.readUTF();
            Date birthday = DateUtility.deserialize(input);
            return new Person(firstname, surname, birthday);
        } catch (Throwable exception){
            throw new IOException(exception);
        }
    }

    @Override
    public int compareTo(Person otherPerson) {
        if (firstName.equals(otherPerson.firstName)) {
            if (surname.equals(otherPerson.surname))
                return birthdate.compareTo(otherPerson.getBirthdate());
        } else
            return firstName.compareTo(otherPerson.getFirstName());
        return 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        return firstName + " " + surname + " " + new SimpleDateFormat("yyyy-MM-dd").format(birthdate);
    }

}