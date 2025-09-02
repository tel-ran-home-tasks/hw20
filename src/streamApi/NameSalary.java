package streamApi;

public class NameSalary {
    public final String name;
    public final int salary;

    public NameSalary(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
    @Override public String toString() { return name + " : " + salary; }
}
