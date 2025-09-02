package streamApi;

public class Employee {
    public int id;
    public String name;
    public String company;
    public int salary;

    public Employee(int id, String name, String company, int salary) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', company='" + company + "', salary=" + salary + "}";
    }
}
