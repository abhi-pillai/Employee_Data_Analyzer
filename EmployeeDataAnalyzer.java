import java.util.Scanner;
public class EmployeeDataAnalyzer {
    public static void main(String[] args) {
        EmployeeManager employeeManager = new EmployeeManager();
        EmployeeDB employeeDB = new EmployeeDB(employeeManager);

        employeeDB.loadEmployees();

        Scanner sc = new Scanner(System.in);
        System.out.println("------------------Welcome to the Employee Data Analyzer!------------------");
        System.out.println("Enter 1. Insert Employee Data\nEnter 2. Display All Employees\nEnter 3. Delete an Employee\nEnter 4. Update an Employee\nEnter 5. Search an Employee\nEnter 6.Filter data by condition\nEnter 7. Sort Employee by Salary\nEnter 8. Exit");
        String input = "";
        while(!input.equalsIgnoreCase("exit")) {
            System.out.print("Please enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.println("Enter employee data (ID, Name, Department, Salary). Type 'done' to finish:");
                    employeeManager.readEmployeeData(sc);
                    break;
                case 2:
                    System.out.println("All Employees:");
                    employeeManager.getAllEmployees().forEach(employee ->
                        System.out.printf("ID: %-5d | Name: %-15s | Department: %-15s | Salary: %.2f%n",
                                        employee.getId(),
                                        employee.getName(),
                                        employee.getDepartment(),
                                        employee.getSalary().orElse(0.0))
                        );
                    break;
                case 3:
                    System.out.print("Enter Employee ID to delete: ");
                    int deleteId = sc.nextInt();
                    employeeManager.deleteEmployee(deleteId);
                    break;
                case 4:
                    System.out.print("Enter Employee ID to update: "); 
                    int updateId = sc.nextInt();
                    sc.nextLine(); // Consume newline 
                    System.out.print("Enter new Name: ");
                    String newName = sc.nextLine(); 
                    System.out.print("Enter new Department: ");
                    String newDepartment = sc.nextLine();
                    System.out.print("Enter new Salary: ");
                    double newSalary = sc.nextDouble();
                    employeeManager.updateEmployee(updateId, newName, newDepartment, newSalary);
                    break;
                case 5:
                    System.out.print("Enter Employee ID to search: ");
                    int searchId = sc.nextInt();
                    employeeManager.getEmployeeById(searchId).ifPresentOrElse(
                            emp -> System.out.printf("ID: %-5d | Name: %-15s | Department: %-15s | Salary: %.2f%n",
                                        emp.getId(),
                                        emp.getName(),
                                        emp.getDepartment(),
                                        emp.getSalary().orElse(0.0))
                        ,
                            () -> System.out.println("Employee with ID " + searchId + " not found.")
                    );
                    break;
                case 6:
                    System.out.println("Filtering options:\n1. Filter by Salary\n2. Filter by Department");
                    int filterChoice = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    if (filterChoice == 1) {
                        System.out.print("Enter salary to filter: ");
                        double salary = sc.nextDouble();
                        employeeManager.filterEmployeesBySalary(salary).forEach(employee ->
                            System.out.printf("ID: %-5d | Name: %-15s | Department: %-15s | Salary: %.2f%n",
                                            employee.getId(),
                                            employee.getName(),
                                            employee.getDepartment(),
                                            employee.getSalary().orElse(0.0))
                            );
                    } else if (filterChoice == 2) {
                        System.out.print("Enter department to filter: ");
                        String department = sc.nextLine();
                        employeeManager.filterEmployeesByDepartment(department).forEach(employee ->
                            System.out.printf("ID: %-5d | Name: %-15s | Department: %-15s | Salary: %.2f%n",
                                            employee.getId(),
                                            employee.getName(),
                                            employee.getDepartment(),
                                            employee.getSalary().orElse(0.0))
                            );
                    } else {
                        System.out.println("Invalid filter choice.");
                    }
                    break;
                case 7:
                    System.out.println("Employees sorted by salary:"); 
                    employeeManager.sortEmployeesBySalary().forEach(employee ->
                        System.out.printf("ID: %-5d | Name: %-15s | Department: %-15s | Salary: %.2f%n",
                                        employee.getId(),
                                        employee.getName(),
                                        employee.getDepartment(),
                                        employee.getSalary().orElse(0.0))
                        );
                    break;
                case 8:
                    input = "exit";
                    System.out.println("\n------------------Thank you for using the Employee Data Analyzer. Goodbye!------------------\n");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    sc.close();
    }
}