package gb.seminar3;

public class CompareAgeEmployee extends Employee implements Comparable<Employee>{

        public CompareAgeEmployee(String name, int salary, int age) {
            super(name, salary, age);
        }

        @Override
        public int compareTo(Employee o) {
            return this.getAge() - o.getAge();
        }
    @Override
    public String toString() {
        return "{" + "name='" + name + '\'' + ", salary=" + salary + ", age=" + age + "}\n";
    }

}

