package streamApi;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

public class EmployeeService {

    public static void displayAverageMinMaxSalary(List<Employee> employees) {
        IntSummaryStatistics stats = employees.stream()
                .mapToInt(e -> e.salary)
                .summaryStatistics();
        System.out.printf("avg=%.2f, min=%d, max=%d%n",
                stats.getAverage(), stats.getMin(), stats.getMax());
    }

    public static void displayAverageSalary(List<Employee> employees, String company) {
        OptionalDouble avg = employees.stream()
                .filter(e -> Objects.equals(e.company, company))
                .mapToInt(e -> e.salary)
                .average();
        System.out.println(company + " avg salary: " +
                (avg.isPresent() ? String.format("%.2f", avg.getAsDouble()) : "n/a"));
    }

    public static void displayEmployeesAvgSalary(List<Employee> employees) {
        double globalAvg = employees.stream()
                .mapToInt(e -> e.salary)
                .average()
                .orElse(Double.NaN);

        employees.stream()
                .filter(e -> e.salary > globalAvg)
                .map(e -> new NameSalary(e.name, e.salary))
                .forEach(System.out::println);
    }
}
