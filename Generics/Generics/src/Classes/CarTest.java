package Classes;

import org.junit.Assert;
import org.junit.Before;

public class CarTest {

    private static final int SPEED = 300;

    private Car car;

    @Before
    public void before() {
        car = new Car(SPEED);
        Assert.assertEquals(SPEED, car.maxSpeed());
    }

    @org.junit.Test
    public void aggregate() {
        int aggregate = car.aggregate(null);
        Assert.assertEquals(SPEED, aggregate);
        final int init = 20;
        Assert.assertEquals((int) (SPEED + init), (int) (car.aggregate(init)));
    }

    @org.junit.Test
    public void deepClone() {
        Car clone = car.deepClone();
        Assert.assertEquals(car.maxSpeed(), clone.maxSpeed());
        Assert.assertNotSame(car, clone);
    }
}