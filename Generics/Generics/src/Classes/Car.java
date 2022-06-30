package Classes;

import Interfaces.IAggregable;
import Interfaces.IDeeplyCloneable;

public class Car implements IAggregable<Car, Integer>, IDeeplyCloneable<Car>{

    private int maxSpeed;

    public Car(){
    }

    public Car(int maxSpeed){
        this.maxSpeed = maxSpeed;
    }

    public int maxSpeed(){
        return maxSpeed;
    }

    public Integer aggregate(Integer result){
        if(result == null){
            return maxSpeed;
        }
        return maxSpeed + result;
    }

    public Car deepClone(){
        return new Car(maxSpeed);
    }

}