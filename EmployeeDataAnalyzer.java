import java.util.List;

public class EmployeeDataAnalyzer {
    public static void main(String[] args) {
        EmployeeManager employeeManager = new EmployeeManager();

        // Adding employees to the system
        employeeManager.addEmployee(new Employee(1, "Geek1", "Engineering", 75000.0));
        employeeManager.addEmployee(new Employee(2, "Geek2", "Marketing", 68000.0));
        employeeManager.addEmployee(new Employee(3, "Geek3", "Engineering", 80000.0));
        employeeManager.addEmployee(new Employee(4, "Geek4", "HR", 55000.0));

        // Simulate multithreading: Processing employee by ID
        Thread processor1 = new EmployeeProcessor(employeeManager, 1);
        Thread processor2 = new EmployeeProcessor(employeeManager, 2);
        Thread processor3 = new EmployeeProcessor(employeeManager, 5);  // Non-existing employee

        processor1.start();
        processor2.start();
        processor3.start();

        // Wait for threads to complete
        try {
            processor1.join();
            processor2.join();
            processor3.join();
        } catch (InterruptedException e) {
            System.out.println("Error waiting for thread completion: " + e.getMessage());
        }

        // Filtering employees based on salary
        System.out.println("\nEmployees with salary >= 70000:");
        List<Employee> highEarners = employeeManager.filterEmployeesBySalary(70000.0);
        highEarners.forEach(System.out::println);

        // Sorting employees by salary
        System.out.println("\nEmployees sorted by salary:");
        List<Employee> sortedEmployees = employeeManager.sortEmployeesBySalary();
        sortedEmployees.forEach(System.out::println);
    }
}