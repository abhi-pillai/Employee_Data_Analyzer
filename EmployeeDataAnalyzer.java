import java.util.List;

public class EmployeeDataAnalyzer {
    public static void main(String[] args) {
        EmployeeManager employeeManager = new EmployeeManager();
        System.out.println("Enter employee data (ID, Name, Department, Salary). Type 'done' to finish:");
        employeeManager.readEmployeeData();
        

        // Simulate multithreading: Processing employee by ID
        Thread processor1 = new EmployeeProcessor(employeeManager, 1);
        Thread processor2 = new EmployeeProcessor(employeeManager, 2);
        Thread processor3 = new EmployeeProcessor(employeeManager, 5);

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