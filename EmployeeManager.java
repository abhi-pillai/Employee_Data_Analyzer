import java.util.*;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.io.File;
public class EmployeeManager {
    private Map<Integer, Employee> employeeData;
    private EmployeeDB employeeDB;
    private String employeedbFile = "employeedb.csv";
    public EmployeeManager() {
        this.employeeData = new HashMap<>();
        this.employeeDB = new EmployeeDB(this);
        // Load existing employees from DB
        try (Scanner sc = new Scanner(new File(employeedbFile))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String department = parts[2].trim();
                    double salary = Double.parseDouble(parts[3].trim());
                    Employee employee = new Employee(id, name, department, salary);
                    employeeData.put(id, employee);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading employee data: " + e.getMessage());
        }
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
                if(employeeData.containsKey(id)) {
                    System.out.println("Employee with ID " + id + " already exists. Please enter a unique ID.");
                    continue;
                }
                String name = parts[1].trim();
                String department = parts[2].trim();
                double salary = Double.parseDouble(parts[3].trim());
                Employee employee = new Employee(id, name, department, salary);
                addEmployee(employee);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter valid ID and Salary.");
            }
        }
    }

    public void addEmployee(Employee employee) {
        employeeData.put(employee.getId(), employee);
        employeeDB.updateEmployee(employee);
    }

    public void deleteEmployee(int id) {
        if (employeeData.remove(id) != null) {
            employeeDB.deleteEmployee(id);
            System.out.println("Employee with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }
    public void updateEmployee(int id, String name, String department, Double salary) {
        Employee employee = employeeData.get(id);
        if (employee != null) {
            Employee updatedEmployee = new Employee(id, name, department, salary);
            employeeData.put(id, updatedEmployee);
            employeeDB.updateEmployee(updatedEmployee); // Update in DB
            System.out.println("Employee with ID " + id + " updated successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }
    public Optional<Employee> getEmployeeById(int id) {
        return Optional.ofNullable(employeeData.get(id));
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeData.values());
    }
    
    public List<Employee> filterEmployeesBySalary(double salary) {
        return employeeData.values().stream()
                .filter(employee -> employee.getSalary().orElse(0.0) == salary)
                .collect(Collectors.toList());
    }

    public List<Employee> filterEmployeesByDepartment(String department) {
        return employeeData.values().stream()
                .filter(employee -> employee.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    public List<Employee> sortEmployeesBySalary() {
        return employeeData.values().stream()
                .sorted(Comparator.comparingDouble(e -> e.getSalary().orElse(0.0)))
                .collect(Collectors.toList());
    }
}