package gb.seminar3;

public class CompareSalaryEmployee extends Employee implements Comparable<Employee>{

    public CompareSalaryEmployee(String name, int salary, int age) {
        super(name, salary, age);
    }

    @Override
    public int compareTo(Employee o) {
        return this.getSalary() - o.getSalary();
    }

    @Override
    public String toString() {
        return "{" + "name='" + name + '\'' + ", salary=" + salary + ", age=" + age + "}\n";
    }

}
