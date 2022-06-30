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

    // (assignment 03)
    // methods:
    // * is older than other person
    // * is younger than other person
    // * compare age with other person's age

    public boolean isOlderThan(Person person) {
        return this.age > person.getAge();
    }
    public boolean isOlderThan(int age) {
        return this.age > age;
    }

    public boolean isYoungerThan(Person person) {
        return this.age < person.getAge();
    }

    public boolean compareAge(Person person) {
        return getAge() > person.getAge();
    }

}

