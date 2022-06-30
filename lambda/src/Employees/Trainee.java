package Employees;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Trainee extends Employee {

    // attributes:
    // * apprenticeship start date
    // * apprenticeship length (in days)

    private LocalDate apprenticeShipStart;
    private int apprenticeShipLength;

    public Trainee(String firstName, String lastName, LocalDate birthDate, BigDecimal salary, Manager manager,
                   LocalDate apprenticeShipStart, int apprenticeShipLength) {

        super(firstName, lastName, birthDate, salary, manager);
        this.apprenticeShipStart = apprenticeShipStart;
        this.apprenticeShipLength = apprenticeShipLength;
    }

    // (assignment 03)
    // * practice length is shorter than given number of days
    // * practice length is longer than given number of days

    public boolean isAppLonger(int length) {
        return apprenticeShipLength > length;
    }

    public boolean isAppShorter(int length) {
        return apprenticeShipLength < length;
    }

}