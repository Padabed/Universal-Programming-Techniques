package Employees;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Worker extends Employee {

    // attributes
    // * employment date
    // * bonus

    private final LocalDate employmentDate;
    private final BigDecimal bonus;

    public Worker(String firstName, String lastName, LocalDate birthDate, BigDecimal salary, Manager manager,
                  LocalDate employmentDate, BigDecimal bonus) {
        super(firstName, lastName, birthDate, salary, manager);
        this.employmentDate = employmentDate;
        this.bonus = bonus;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

}