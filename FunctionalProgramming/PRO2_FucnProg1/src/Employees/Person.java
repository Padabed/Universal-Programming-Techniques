package Employees;

import java.time.LocalDate;

public class Person {

    private final String firstName, lastName;
    private final LocalDate birthDate;
    private short age;

    public Person(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        calcAge();
    }

    public String getFirstName() { // getter
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public short getAge() {
        calcAge();
        return age;
    }

    public void calcAge() {
        //correct implementation
        age = (short)(LocalDate.now().getYear() - birthDate.getYear());
    }
}

