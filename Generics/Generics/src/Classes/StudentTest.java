package Classes;

import org.junit.Assert;;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTest {

    private static final String firstName = "Nick";
    private static final String secondName = "Marker";
    private static final int firstScholarship = 1550;
    private static final int secondScholarship = 1500;

    final Student firstStudent = new Student(firstName, firstScholarship);
    final Student secondStudent = new Student(secondName, secondScholarship);

    @Test
    public void getNS() {
        Assert.assertEquals(firstName, firstStudent.getName());
        Assert.assertEquals(firstScholarship, (int) firstStudent.getScholarship());

        Assert.assertEquals(secondName, secondStudent.getName());
        Assert.assertEquals(secondScholarship, (int) secondStudent.getScholarship());

        Assert.assertNotEquals(firstStudent.getName(), secondStudent.getName());
        Assert.assertNotEquals(firstStudent.getScholarship(), secondStudent.getScholarship());
    }

    @Test
    public void aggregate() {
        int aggregateOne = firstStudent.aggregate(null);
        Assert.assertEquals(firstScholarship, aggregateOne);

        int aggregateTwo = secondStudent.aggregate(null);
        Assert.assertEquals(secondScholarship, aggregateTwo);

    }

    @Test
    public void deepClone() {
        Student firstClone = firstStudent.deepClone();
        Student secondClone = secondStudent.deepClone();

        Assert.assertNotEquals(firstStudent, firstClone);
        Assert.assertNotEquals(secondStudent, secondClone);

        Assert.assertEquals(firstStudent.getName(), firstClone.getName());
        Assert.assertEquals(secondStudent.getName(), secondClone.getName());

    }

}