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
    public boolean isAppLonger(int length) {
        return apprenticeShipLength > length;
    }
    public boolean isAppShorter(int length) {
        return apprenticeShipLength < length;
    }
}