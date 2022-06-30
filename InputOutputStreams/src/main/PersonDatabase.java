package main;

import comparators.BirthdateComparator;
import comparators.FirstNameComparator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public final class PersonDatabase {

    private List<Person> list;

    public PersonDatabase() {
        this.list = InputParser.parse(new File("src//data//PeopleInfo.txt"));
    }

    public PersonDatabase(List<Person> list) {
        this.list = list;
    }

    public PersonDatabase(File file) {
        this.list = InputParser.parse(file);
    }

    public void serialize(DataOutputStream output) throws IOException {
        try {
            output.writeInt(sortedBySurnameFirstNameAndBirthdate().size());
            sortedBySurnameFirstNameAndBirthdate().forEach(person -> {
                try {
                    person.serialize(output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Throwable exception){
            throw new IOException(exception);
        }
    }

    public static PersonDatabase deserialize(DataInputStream input) throws IOException {
        try {
            int size = input.readInt();
            List<Person> persons = new ArrayList<>();
            for(int i = 0; input.available() > 0 && i < size; i++){
                Person person = Person.deserialize(input);
                persons.add(person);
            }
            return new PersonDatabase(persons);
        } catch (Throwable exception) {
            throw new IOException();
        }
    }

    public List<Person> sortedByFirstName() {
        list.sort(new FirstNameComparator());
        return list;
    }

    public List<Person> sortedBySurnameFirstNameAndBirthdate() {
        list.sort(Person::compareTo);
        return list;
    }

    public List<Person> sortedByBirthdate() {
        list.sort(new BirthdateComparator());
        return list;
    }

    public List<Person> bornOnDay(Date date) {
        Map<Integer, Person> m = new HashMap<>();

        for (int i = 0; i < list.size(); i++)
            m.put(i, list.get(i));

        return m
                .values()
                .stream()
                .filter(e -> e.getBirthdate().equals(date))
                .collect(Collectors.toList());
    }

    public List<Person> getList() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }

}