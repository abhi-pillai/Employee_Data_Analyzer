# Employee Data Analyzer

A Java-based command-line application for managing employee records with persistent CSV storage. This application allows you to perform CRUD operations, filter, search, and analyze employee data efficiently.

## Features

- **Add Employees**: Insert new employee records with ID, name, department, and salary
- **View All Employees**: Display all employee records in a formatted list
- **Search**: Find specific employees by their ID
- **Update**: Modify existing employee information
- **Delete**: Remove employee records from the system
- **Filter**: 
  - Filter employees by minimum salary threshold
  - Filter employees by department
- **Sort**: Sort employees by salary in ascending order
- **Persistent Storage**: All data is automatically saved to `employeedb.csv`

## Project Structure

```
├── Employee.java                    # Employee model class
├── EmployeeManager.java             # Business logic and data management
├── EmployeeDB.java                  # Database (CSV) operations
├── EmployeeDataAnalyzer.java        # Main application with CLI menu
├── EmployeeNotFoundException.java   # Custom exception class
└── employeedb.csv                   # Persistent data storage (auto-generated)
```

## Requirements

- Java Development Kit (JDK) 8 or higher
- Java compiler (javac)

## Installation & Setup

1. Clone or download all Java files to a directory
2. Navigate to the project directory in your terminal

## Compilation

Compile all Java files:

```bash
javac *.java
```

## Running the Application

Execute the main class:

```bash
java EmployeeDataAnalyzer
```

## Usage

Upon running the application, you'll see a menu with the following options:

```
------------------Welcome to the Employee Data Analyzer!------------------
Enter 1. Insert Employee Data
Enter 2. Display All Employees
Enter 3. Delete an Employee
Enter 4. Update an Employee
Enter 5. Search an Employee
Enter 6. Filter data by condition
Enter 7. Sort Employee by Salary
Enter 8. Exit
```

### 1. Insert Employee Data

Enter employee information in the format: `ID, Name, Department, Salary`

Example:
```
101, John Doe, Engineering, 75000
102, Jane Smith, HR, 65000
done
```

Type `done` when finished adding employees.

### 2. Display All Employees

Shows all employee records with their ID, name, and salary.

### 3. Delete an Employee

Enter the employee ID to remove the record from the system.

### 4. Update an Employee

Provide the employee ID and enter new values for name, department, and salary.

### 5. Search an Employee

Enter an employee ID to view their complete details.

### 6. Filter Data by Condition

Choose from two filtering options:
- **Filter by Salary**: Display employees earning more than a specified amount
- **Filter by Department**: Display all employees in a specific department

### 7. Sort Employee by Salary

Displays all employees sorted by salary in ascending order.

### 8. Exit

Closes the application. All data is automatically saved.

## Data Persistence

- Employee data is stored in `employeedb.csv`
- The file is automatically created on first run
- Data is loaded when the application starts
- Changes are immediately written to the CSV file

## Class Descriptions

### Employee.java
- Model class representing an employee
- Uses `Optional<Double>` for salary to handle null values safely
- Provides getters for all fields

### EmployeeManager.java
- Manages employee data using a `HashMap<Integer, Employee>`
- Handles all business logic (add, update, delete, search, filter, sort)
- Uses Java Streams for filtering and sorting operations
- Validates duplicate IDs and input formats

### EmployeeDB.java
- Manages CSV file operations
- Handles reading from and writing to `employeedb.csv`
- Synchronizes data between memory and persistent storage

### EmployeeDataAnalyzer.java
- Main application entry point
- Provides interactive command-line interface
- Handles user input and menu navigation

### EmployeeNotFoundException.java
- Custom exception class for handling missing employee scenarios

## Example Session

```
Please enter your choice: 1
Enter employee data (ID, Name, Department, Salary). Type 'done' to finish:
101, Alice Johnson, IT, 85000
102, Bob Williams, Finance, 72000
done

Please enter your choice: 2
All Employees:
ID: 101   | Name: Alice Johnson  | Salary: 85000.00
ID: 102   | Name: Bob Williams   | Salary: 72000.00

Please enter your choice: 6
Filtering options:
1. Filter by Salary
2. Filter by Department
1
Enter salary to filter: 80000
ID: 101   | Name: Alice Johnson  | Salary: 80000.00
```

## Error Handling

- Validates input format for employee data
- Checks for duplicate employee IDs
- Handles number format exceptions
- Provides user-friendly error messages

## Future Enhancements

- Export reports to different file formats (PDF, Excel)
- Advanced search with multiple criteria
- Department-wise salary statistics
- Employee performance tracking
- Role-based access control

## License

This project is open-source and available for educational purposes.

## Author

Created as a command-line data management application demonstrating Java fundamentals, file I/O, and object-oriented programming principles.