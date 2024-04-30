package Nigar;

import org.testng.annotations.Test;
import utilities.DatabaseHelper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class DatabaseTests extends DatabaseHelper {
    @Test
    public void Test8() throws SQLException {

        // 8- Calculate the average salary for each department (by department number or department name)

        DBConnectionOpen();
        ResultSet rs = queryScreen.executeQuery("SELECT de.dept_no, AVG(s.salary) AS average_salary\n" +
                "FROM dept_emp de\n" +
                "JOIN salaries s ON de.emp_no = s.emp_no\n" +
                "GROUP BY de.dept_no;");

        ResultSetMetaData rsmd = rs.getMetaData();

        System.out.println("Calculated By Department Number: \n ");
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.printf("%-10s", rsmd.getColumnName(i));
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.printf("%-10s", rs.getString(i));
            }
            System.out.println();
        }

        System.out.println("\nCalculated By Department Name: \n ");
        ResultSet rs2 = queryScreen.executeQuery("SELECT dept_name, AVG(salary) AS avg_salary\n" +
                "FROM departments\n" +
                "JOIN dept_emp ON departments.dept_no = dept_emp.dept_no\n" +
                "JOIN salaries ON dept_emp.emp_no = salaries.emp_no\n" +
                "GROUP BY departments.dept_no;");

        ResultSetMetaData rsmd2 = rs2.getMetaData();

        for (int i = 1; i <= rsmd2.getColumnCount(); i++) {
            System.out.printf("%-20s", rsmd2.getColumnName(i));
        }
        System.out.println();

        while (rs2.next()) {
            for (int i = 1; i <= rsmd2.getColumnCount(); i++) {
                System.out.printf("%-20s", rs2.getString(i));
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void Test9() throws SQLException {

        //  9. Calculate the average salary for each department, including department names

        DBConnectionOpen();
        ResultSet rs = queryScreen.executeQuery("SELECT d.dept_no, d.dept_name, AVG(s.salary) AS average_salary\n" +
                "FROM employees e\n" +
                "JOIN dept_emp de ON e.emp_no = de.emp_no\n" +
                "JOIN salaries s ON e.emp_no = s.emp_no\n" +
                "JOIN departments d ON de.dept_no = d.dept_no\n" +
                "GROUP BY d.dept_no, d.dept_name;");

        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.printf("%-20s", rsmd.getColumnName(i));
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.printf("%-20s", rs.getString(i));
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void Test14() throws SQLException {
        // 14. List the first name, last name, and highest salary of employees in the "Sales" department.
        // Order the list by highest salary descending and only show the employee with the highest salary.

        DBConnectionOpen();
        ResultSet rs = queryScreen.executeQuery("SELECT e.first_name, e.last_name, d.dept_name, MAX(s.salary) AS highest_salary\n" +
                "FROM employees e\n" +
                "INNER JOIN dept_emp de ON e.emp_no = de.emp_no\n" +
                "INNER JOIN departments d ON de.dept_no = d.dept_no\n" +
                "INNER JOIN salaries s ON e.emp_no = s.emp_no\n" +
                "WHERE d.dept_name = 'Sales'\n" +
                "GROUP BY e.first_name, e.last_name,d.dept_name\n" +
                "ORDER BY highest_salary DESC\n" +
                "LIMIT 1;");

        while (rs.next()) {
            System.out.println("First Name: " + rs.getString("first_name") +
                    "\nLast Name: " + rs.getString("last_name") +
                    "\nDepartment: " + rs.getString("dept_name") +
                    "\nSalary: " + rs.getString("highest_salary"));
        }
        DBConnectionClose();
    }

    @Test
    public void Test16() throws SQLException {
        // 16. For each department, identify the employee with the highest single salary ever recorded. List the
        //     department name, employee's first name, last name, and the peak salary amount. Order the results
        //     by the peak salary in descending order.

        DBConnectionOpen();
        List<List<String>> returnedData = getListData("SELECT d.dept_name AS department, e.first_name, e.last_name, MAX(s.salary) AS max_salary\n" +
                "FROM employees e\n" +
                "INNER JOIN dept_emp de ON e.emp_no = de.emp_no\n" +
                "INNER JOIN departments d ON de.dept_no = d.dept_no\n" +
                "INNER JOIN salaries s ON e.emp_no = s.emp_no\n" +
                "GROUP BY d.dept_name\n" +
                "ORDER BY max_salary DESC;");

        System.out.printf("%-20s%-20s%-20s%-20s%n", "Department:", "First Name:", "Last Name:", "Salary:");
        for (List<String> row : returnedData) {
            for (String column : row) {
                System.out.printf("%-20s", column);
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void Test18() {
        // 18. List the names, last names, and hire dates in
        // alphabetical order of all employees hired before January 01, 1990.

        DBConnectionOpen();
        List<List<String>> returnedData = getListData("SELECT first_name, last_name, hire_date\n" +
                "FROM employees\n" +
                "WHERE hire_date < '1990-01-01'\n" +
                "ORDER BY first_name ASC, last_name ASC;");

        System.out.printf("%-20s%-20s%-20s%n", "First Name:", "Last Name:", "Hire Date:");

        int count = 0;
        for (List<String> row : returnedData) {
            if (count >= 100) {
                break;
            }
            for (String column : row) {
                System.out.printf("%-20s", column);
            }
            System.out.println();
            count++;
        }
        DBConnectionClose();
    }

    @Test
    public void Test21() throws SQLException {
        //21- a: Find the count of male employees (179973)
        //  - b: Determine the count of female employees (120050)
        //  - c: Find the number of male and female employees by grouping:
        //  - d: Calculate the total number of employees in the company (300023)

        DBConnectionOpen();

        System.out.printf("%-20s%-20s%-20s%n", "Male Count:", "Female Count:", "Total Employees:");

        // a
        ResultSet rs1 = queryScreen.executeQuery("SELECT COUNT(gender) AS Male_Count FROM employees WHERE gender = 'M'; ");
        rs1.next();
        System.out.printf("%-20s", rs1.getString("Male_Count"));

        // b
        ResultSet rs2 = queryScreen.executeQuery("SELECT COUNT(gender) AS Female_Count FROM employees WHERE gender = 'F';");
        rs2.next();
        System.out.printf("%-20s", rs2.getString("Female_Count"));

        // c
        ResultSet rs3 = queryScreen.executeQuery("SELECT COUNT(emp_no) AS Total_Employees FROM employees; ");
        rs3.next();
        System.out.printf("%-20s", rs3.getString("Total_Employees"));
        System.out.println();

        // d
        ResultSet rs4 = queryScreen.executeQuery("SELECT gender, COUNT(*) AS count FROM employees GROUP BY gender;");
        while (rs4.next()) {
            String gender = rs4.getString("gender");
            String count = rs4.getString("count");
            System.out.printf("\nGender: %-11s Count: %-11s\n", gender, count);
        }
        DBConnectionClose();
    }
}
