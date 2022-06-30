package Classes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContainerTest {

    public Container<Car, Integer> container = new Container<>();
    public Container<Student, Integer> container2 = new Container<>();

    @BeforeEach
    public void before() {

        container.elements().add(new Car(200));
        container.elements().add(new Car(250));
        container.elements().add(new Car(300));
        container.elements().add(new Car(350));

        container2.adds(new Student("Nick", 1555));
        container2.adds(new Student("Marker", 1500));

    }

    @Test
    public void testAggregateAllElements() {

        Integer checker = container.aggregateElements();
        Assert.assertEquals((Integer) 1100, checker);

    }

    @Test
    public void testCloneElementByIndex() {

        Car cloneCar = container.cloneElementByIndex(0);
        Assert.assertEquals(cloneCar.maxSpeed(), container.get(0).maxSpeed());
        Assert.assertNotEquals(cloneCar, container.get(1));

    }

    @Test
    public void testElementsAdds() {

        List<Student> list = new ArrayList<>();
        list.add(new Student("Nick", 1550));
        list.add(new Student("Marker", 1500));

        Assert.assertEquals(list.get(1).getName(), container2.elements().get(1).getName());

    }

}