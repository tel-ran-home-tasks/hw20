package streamApi;

import java.util.List;

public class MainAppl {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee(1, "Nik", "Yandex", 25000),
            new Employee(2, "Moshe",   "Amazon", 22000),
            new Employee(3, "Vasya", "Google", 31000),
            new Employee(4, "Vic",  "Intel",   28000),
            new Employee(5, "Masha",   "Nvidia", 26000),
            new Employee(5, "Sasha",   "Google", 35000),
            new Employee(5, "Misha",   "Amazon", 29000)
        );

        EmployeeService.displayAverageMinMaxSalary(employees);

        EmployeeService.displayAverageSalary(employees, "Google");
        EmployeeService.displayAverageSalary(employees, "Amazon");
        System.out.println("Employees with salary > global average:");
        EmployeeService.displayEmployeesAvgSalary(employees);

        int[] sorted = {1, 2, 3, 4, 5, 6};
        System.out.print("Shuffled: ");
        NumbersUtil.displayShuffledArray(sorted);

        System.out.print("SportLoto (7 из 49): ");
        NumbersUtil.sportLoto(1, 49, 7);
    }
}
