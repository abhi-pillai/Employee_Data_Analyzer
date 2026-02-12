import java.util.*;
import java.util.stream.Collectors;
import java.util.Scanner;
public class EmployeeManager {
    private Map<Integer, Employee> employeeData;

    public EmployeeManager() {
        this.employeeData = new HashMap<>();
    }

    public void readEmployeeData(Scanner sc) {
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            String[] parts = input.split(",");
            if (parts.length != 4) {
                System.out.println("Invalid input. Please enter data in the format: ID, Name, Department, Salary");
                continue;
            }
            try {
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String department = parts[2].trim();
                double salary = Double.parseDouble(parts[3].trim());
                Employee employee = new Employee(id, name, department, salary);
                addEmployee(employee);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter valid ID and Salary.");
            }
        }
        sc.close();
    }

    public void addEmployee(Employee employee) {
        employeeData.put(employee.getId(), employee);
    }

    public Optional<Employee> getEmployeeById(int id) {
        return Optional.ofNullable(employeeData.get(id));
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeData.values());
    }

    public List<Employee> filterEmployeesBySalary(Double minSalary) {
        return employeeData.values().stream()
                .filter(employee -> employee.getSalary().orElse(0.0) >= minSalary)
                .collect(Collectors.toList());
    }

    public List<Employee> sortEmployeesBySalary() {
        return employeeData.values().stream()
                .sorted(Comparator.comparingDouble(e -> e.getSalary().orElse(0.0)))
                .collect(Collectors.toList());
    }
}