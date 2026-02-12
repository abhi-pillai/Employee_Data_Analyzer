import java.util.List;
import java.util.Scanner;

public class EmployeeDataAnalyzer {
    public static void main(String[] args) {
        EmployeeManager employeeManager = new EmployeeManager();
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------Welcome to the Employee Data Analyzer!-----------");
        System.out.println("Enter 1. Insert Employee Data\nEnter 2. Display All Employees\nEnter 3. Filter Employees\nEnter 4. Sort Employees\nEnter 5. Find Employee by ID\nEnter 6. Exit");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline
        
        switch (choice) {
            case 1:
                System.out.println("Enter employee data (ID, Name, Department, Salary). Type 'done' to finish:");
                employeeManager.readEmployeeData(sc);
                break;
            case 2:
                System.out.println("All Employees:");
                employeeManager.getAllEmployees().forEach(System.out::println);
                break;
            case 3:
                System.out.println("Enter minimum salary to filter employees:");
                double minSalary = sc.nextDouble();
                sc.nextLine(); // Consume newline
                List<Employee> filteredEmployees = employeeManager.filterEmployeesBySalary(minSalary);
                System.out.println("Employees with salary >= " + minSalary + ":");
                filteredEmployees.forEach(System.out::println);
                break;
            case 4:
                System.out.println("Employees sorted by salary:");
                List<Employee> sortedEmployees = employeeManager.sortEmployeesBySalary();
                sortedEmployees.forEach(System.out::println);
                break;
            case 5:
                System.out.println("Enter employee ID to find:");
                int id = sc.nextInt();
                sc.nextLine(); // Consume newline
                EmployeeProcessor processor = new EmployeeProcessor(employeeManager, id);
                processor.start();
                break;
            case 6:
                System.out.println("Exiting the Employee Data Analyzer.");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

    }
}