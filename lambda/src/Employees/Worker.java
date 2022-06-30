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

    // (assignment 03)
    // attributes:
    // * has bonus
    //
    // methods:
    // * seniority is longer than given number of years (seniority - sta¿)
    // * seniority is longer than given number of months
    // * has bonus greater than given amount of money

    private boolean hasBonus;

    public int getMonthOfSen() {
        return (this.employmentDate.getMonthValue() + ((LocalDate.now().getYear() -this.employmentDate.getYear()) * 12) );
    }

    public int getYearOFSen() {
        return LocalDate.now().getYear() - this.employmentDate.getYear();
    }

    public boolean isSenGreaterByYear(int years) {
        return getYearOFSen() > years;
    }
    public boolean isSenGreaterByMonth(int months) {
        return getMonthOfSen() > months;
    }
    public boolean isBonGreaterThan(BigDecimal bonus) {
        return this.bonus.compareTo(bonus) > 0;
    }

    public boolean hasBonus() {
        return hasBonus;
    }

}