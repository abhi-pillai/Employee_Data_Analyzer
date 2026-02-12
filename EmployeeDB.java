import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class EmployeeDB {
    private File employeedbFile = new File("employeedb.csv");
    EmployeeManager employeeManager;

    EmployeeDB(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
        try {
            if (!employeedbFile.exists()) {
                employeedbFile.createNewFile();
            }
        }
        catch (Exception e) {
            System.out.println("Error initializing EmployeeDB: " + e.getMessage());
        }
    }



    public void loadEmployees() {
        try (Scanner scanner = new Scanner(employeedbFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String department = parts[2];
                double salary = Double.parseDouble(parts[3]);

                employeeManager.addEmployee(
                    new Employee(id, name, department, salary)
                );
            }
        } catch (Exception e) {
            System.out.println("Error loading employees: " + e.getMessage());
        }
    }

    public void deleteEmployee(int id) {
        try {
            PrintWriter writer = new PrintWriter(employeedbFile);
            for (Employee employee : employeeManager.getAllEmployees()) {
                if (employee.getId() != id) {
                    writer.println(employee.getId() + "," + employee.getName() + "," + employee.getDepartment() + "," + employee.getSalary().orElse(0.0));
                }
            }
            writer.close();
        }catch (IOException e) {
            System.out.println("Error writing to EmployeeDB: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Error initializing EmployeeDB: " + e.getMessage());
        }
    }
    public void updateEmployee(Employee employee) {
        try {
            PrintWriter writer = new PrintWriter(employeedbFile);
            for (Employee emp : employeeManager.getAllEmployees()) {
                if (emp.getId() == employee.getId()) {
                    writer.println(employee.getId() + "," + employee.getName() + "," + employee.getDepartment() + "," + employee.getSalary().orElse(0.0));
                } else {
                    writer.println(emp.getId() + "," + emp.getName() + "," + emp.getDepartment() + "," + emp.getSalary().orElse(0.0));
                }
            }
            writer.close();
        }catch (IOException e) {
            System.out.println("Error writing to EmployeeDB: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Error initializing EmployeeDB: " + e.getMessage());
        }
    }
}
