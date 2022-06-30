package Employees;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Employee extends Person {

    //
    // attributes:
    // * salary (use BigDecimal type for representing currency values)
    // * manager (empty if at top of hierarchy)

    private BigDecimal salary;

    private Manager manager;

    protected Employee(String firstName, String lastName, LocalDate birthDate, BigDecimal salary, Manager manager) {
        super(firstName, lastName, birthDate);
        this.salary = salary;
        this.manager = manager;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Manager getManager() {
        return manager;
    }

    // (assignment 03)
    // methods:
    // * salary is greater than given amount of money
    // * salary is less than given amount of money
    // * compare salary with other employee salary

    public boolean isSalaryGreater(BigDecimal money) {
        return this.salary.compareTo(money) > 0;
    }
    public boolean isSalarySmaller(BigDecimal money) {
        return this.salary.compareTo(money) < 0;
    }
    public int compareSalary(BigDecimal money) {
        return this.salary.compareTo(money);
    }

}
