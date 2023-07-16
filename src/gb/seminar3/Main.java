package gb.seminar3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Employee[] employees = {new Employee("Иванов", 50000, 35),
                                new Employee("Петров", 70000, 24),
                                new Employee("Сидоров", 35000, 47),
                                new Employee("Сичкин", 40000, 45),
                                new Employee("Фторов", 60000, 66),
                                new Employee("Йодидзе", 25000, 28),
                                new Chief("Главнюков", 90000, 65)
        };

        final int age = 45;
        final int inc = 5000;

        increaser(employees, age, inc);

        for (int i=0; i < employees.length; i++) {
            System.out.println(employees[i]);
        }

        List<CompareAgeEmployee> ageEmpl = new ArrayList<>();

        for (int i=0; i < employees.length; i++) {
            CompareAgeEmployee cae = new CompareAgeEmployee(employees[i].getName(),
                                            employees[i].getSalary(), employees[i].getAge());
            ageEmpl.add(cae);
        }
        Collections.sort(ageEmpl);
        System.out.println("\n");
        ageEmpl.forEach(a -> System.out.print(a));

        List<CompareSalaryEmployee> SalaryEmpl = new ArrayList<>();

        for (int i=0; i < employees.length; i++) {
            CompareSalaryEmployee cae = new CompareSalaryEmployee(employees[i].getName(),
                    employees[i].getSalary(), employees[i].getAge());
            SalaryEmpl.add(cae);
        }
        Collections.sort(SalaryEmpl);
        System.out.println("\n");
        SalaryEmpl.forEach(a -> System.out.print(a));
    }

    public static void increaser(Employee[] emp, int age, int inc) {
        for (int i=0; i < emp.length; i++) {
            if (emp[i].getAge() > age) {
                if (!(emp[i] instanceof Chief))
                    emp[i].incSalary(inc);
            }
        }
    }
}
