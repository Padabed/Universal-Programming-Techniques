package main;

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