package PayRoll;

import Employees.Employee;
import java.math.BigDecimal;

public final class PayrollEntry {

    private final Employee employee;
    private final BigDecimal salaryPlusBonus;

    public PayrollEntry(Employee employee, BigDecimal salary, BigDecimal bonus) {
        if (salary ==null || bonus == null){
            throw new NullPointerException("salary or bonus is invalid");
        }
        this.employee = employee;
        this.salaryPlusBonus = salary.add(bonus); // validate whether salary and bonus are not null
    }
}