import java.util.*;
import java.util.stream.Collectors;

public class EmployeeManager {
    private Map<Integer, Employee> employeeData;

    public EmployeeManager() {
        this.employeeData = new HashMap<>();
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