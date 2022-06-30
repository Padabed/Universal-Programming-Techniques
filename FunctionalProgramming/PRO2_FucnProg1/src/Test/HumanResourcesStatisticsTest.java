package Test;

import Employees.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class HumanResourcesStatisticsTest {
    private Manager manager;
    private Worker w1, w2, w3, w4, w5, w6, w7, w8, w9, w10;
    private Trainee t1, t2, t3, t4, t5, t6;
    private Manager manager1, manager2, manager3;

    private LinkedList<Employee> managerSub;

    private LinkedList<Employee> manager1Sub;
    private LinkedList<Employee> manager2Sub;
    private LinkedList<Employee> manager3Sub;

    // Create a HR structure which resembles the below one:
    //
    // Director (an instance of Manager class (Director does not have a manager)
    //   |- Manager01
    //        |- Worker
    //        |- Worker

    //        |- Trainee
    //        |- ...
    //   |- Manager02
    //        |- ...
    //   |- ...
    //   |- Worker
    //   |- Worker
    //   |- Trainee

    private List<Employee> _allEmployees; // all employees ---  i.e. Workers, Trainees and their Managers and top Director (also an instance of Manager class)

    @Test
    public void bonusTotal() {
        BigDecimal total = HumanResourcesStatistics.bonusTotal(_allEmployees);
        Assert.assertEquals(total, new BigDecimal(14844));
    }

    @Test
    public void getLongestSeniorityEmployee() {
        Assert.assertEquals(HumanResourcesStatistics.getLongestSeniorityEmployee(_allEmployees), manager);
    }
    @Test
    public void getHighestSalary() {
        Assert.assertEquals(HumanResourcesStatistics.getHighestSalary(_allEmployees),
                BigDecimal.valueOf(20000));
    }
    @Test
    public void getHighestSalaryWithBonus() {
        Assert.assertEquals(HumanResourcesStatistics.getHighestSalaryWithBonus(_allEmployees),
                BigDecimal.valueOf(30000));
    }
    @Test
    public void getSurnameBeginWithA() {
        Assert.assertEquals(HumanResourcesStatistics.getSurnameBeginWithA(_allEmployees),
                List.of(w6));
    }
    @Test
    public void moreThan1000() {
        Assert.assertEquals(HumanResourcesStatistics.moreThan1000(_allEmployees),
                _allEmployees);
    }
    @Test
    public void payroll() {
        HumanResourcesStatistics.payroll(_allEmployees);
    }

    @Test
    public void subordinatesPayroll() {
        HumanResourcesStatistics.subordinatesPayroll(manager2);
    }

    @SuppressWarnings("Duplicates")
    @Before
    public void init() {
        manager = new Manager("Nikita", "Padabed", LocalDate.of(1950,7,21),
                new BigDecimal(20000), null, LocalDate.of(1980,7,12), new BigDecimal(10000), managerSub);

        manager1 = new Manager("Artem", "lebidok", LocalDate.of(1987,5,20),
                new BigDecimal(3490), manager, LocalDate.of(2020,5,21), new BigDecimal(300), manager1Sub);
        manager2 = new Manager("Ekaterina", "Skotskaia", LocalDate.of(1951,6,4),
                new BigDecimal(5500), manager, LocalDate.of(2019,1,18), new BigDecimal(550), manager2Sub);
        manager3 = new Manager("Mark", "Twice", LocalDate.of(1994,1,15),
                new BigDecimal(3500), manager, LocalDate.of(2016,8,12), new BigDecimal(800), manager3Sub);

        w1 = new Worker("Lion",  "Lebizni", LocalDate.of(1956,2,25),
                new BigDecimal(3954), manager1, LocalDate.of(2019,5,21), new BigDecimal(200));
        w2 = new Worker("Omar", "Nickson", LocalDate.of(1970,5,25),
                new BigDecimal(1133), manager2, LocalDate.of(2018,5,12), new BigDecimal(334));
        w3 = new Worker("Missy", "Lozano", LocalDate.of(1985,7,27),
                new BigDecimal(1122), manager2, LocalDate.of(2015,5,6), new BigDecimal(385));
        w4 = new Worker("Osphalt", "Kobbalpot", LocalDate.of(1989,12,17),
                new BigDecimal(3772), manager3, LocalDate.of(2017,5,1), new BigDecimal(177));
        w5 = new Worker("John", "Tesla", LocalDate.of(1976,5,14),
                new BigDecimal(2312), manager3, LocalDate.of(2013,5,2), new BigDecimal(66));
        w6 = new Worker("Oskar", "Arno", LocalDate.of(1976,6,18),
                new BigDecimal(1559), manager1, LocalDate.of(2018,5,5), new BigDecimal(468));
        w7 = new Worker("Maidi", "Carrow", LocalDate.of(1999,7,11),
                new BigDecimal(1877), manager1, LocalDate.of(2019,5,10), new BigDecimal(384));
        w8 = new Worker("Farrow", "Lee", LocalDate.of(1987,10,15),
                new BigDecimal(1268), manager2, LocalDate.of(2020,5,28), new BigDecimal(391));
        w9 = new Worker("Arianna", "Grante", LocalDate.of(1987,4,14),
                new BigDecimal(2470), manager2, LocalDate.of(2020,5,21), new BigDecimal(451));
        w10 = new Worker("Yanis", "Konski", LocalDate.of(1950,1,17),
                new BigDecimal(3462), manager3, LocalDate.of(2013,5,13), new BigDecimal(338));

        t1 = new Trainee("Kie", "Lambre", LocalDate.of(1999,2,10),
                new BigDecimal(1711), manager1, LocalDate.of(2019,3,12), 60);
        t2 = new Trainee("Rammor", "Traut", LocalDate.of(1996,5,9),
                new BigDecimal(1894), manager1, LocalDate.of(2020,6,16), 120);
        t3 = new Trainee("Ridwan", "Kirkpatrick", LocalDate.of(1989,3,6),
                new BigDecimal(1352), manager3, LocalDate.of(2020,2,26), 365);
        t4 = new Trainee("Borys", "Counter", LocalDate.of(1995,9,25),
                new BigDecimal(1534), manager1, LocalDate.of(2018,8,11), 365);
        t5 = new Trainee("Fionn", "Orozco", LocalDate.of(1991,10,30),
                new BigDecimal(1715), manager2, LocalDate.of(2017,12,10), 100);
        t6 = new Trainee("Elise", "Marsh", LocalDate.of(1990,12,10),
                new BigDecimal(1794), manager2, LocalDate.of(2020,7,17), 30);

        managerSub = new LinkedList<>();
        managerSub.add(manager1);
        managerSub.add(manager2);
        managerSub.add(manager3);
        manager.setSubordinates(managerSub);

        manager1Sub = new LinkedList<>();
        manager1Sub.add(w1);
        manager1Sub.add(w6);
        manager1Sub.add(w7);
        manager1Sub.add(t1);
        manager1Sub.add(t2);
        manager1Sub.add(t4);
        manager1.setSubordinates(manager1Sub);

        manager2Sub = new LinkedList<>();
        manager2Sub.add(w2);
        manager2Sub.add(w3);
        manager2Sub.add(w8);
        manager2Sub.add(w9);
        manager2Sub.add(t5);
        manager2Sub.add(t6);
        manager2.setSubordinates(manager2Sub);

        manager3Sub = new LinkedList<>();
        manager3Sub.add(w4);
        manager3Sub.add(w5);
        manager3Sub.add(w10);
        manager3Sub.add(t3);
        manager3.setSubordinates(manager3Sub);


        _allEmployees = new LinkedList<>();
        _allEmployees.add(manager);
        _allEmployees.add(manager1);
        _allEmployees.add(manager2);
        _allEmployees.add(manager3);
        _allEmployees.addAll(manager1Sub);
        _allEmployees.addAll(manager2Sub);
        _allEmployees.addAll(manager3Sub);

    }
}
