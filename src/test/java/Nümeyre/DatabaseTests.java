package Nümeyre;

import org.testng.annotations.Test;
import utilities.DatabaseHelper;

import java.sql.*;
import java.util.List;

public class DatabaseTests extends DatabaseHelper {
//7-34-27-35-20-28

    @Test
    public void Test7() throws SQLException {
// 7-This query retrieves employees who have salaries between 50000 and 100000.

        DBConnectionOpen();
        List<List<String>> returnedData = getListData("\n" +
                "select e.emp_no, e.first_name, e.last_name, s.salary\n" +
                " from employees.employees  as e\n" +
                " join employees.salaries as s  on e.emp_no = s.emp_no\n" +
                " where s.salary  between 50000  and 100000;");
        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-10s", columns);
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void Test34() throws SQLException {
        // 34- Finding the number of employees per department
        DBConnectionOpen();
        List<List<String>> returnedData = getListData(
                "select departments.dept_name,count(*)" +
                        "from employees" +
                        "left join dept_emp ON dept_emp.emp_no=employees.emp_no" +
                        " left join departments ON departments.dept_no=dept_emp.dept_no" +
                        "left join departments ON departments.dept_no = dept_emp.dept_no" +
                        " o group by departments.dept_no;");
        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-10s", columns);
            }
            System.out.println();
        }

        DBConnectionClose();

    }

    @Test
    public void Test27() throws SQLException {
        // 27 -List all employees and managers in the D005 department

        DBConnectionOpen();
        List<List<String>> returnedData = getListData("SELECT e.emp_no, e.first_name, e.last_name, e.birth_date, e.gender, e.hire_date, d.dept_name AS department_name, t.title, s.salary" +
                "FROM employees e" +
                "JOIN dept_manager dm ON e.emp_no = dm.emp_no" +
                "JOIN departments d ON dm.dept_no = d.dept_no" +
                "JOIN titles t ON e.emp_no = t.emp_no" +
                "JOIN salaries s ON e.emp_no = s.emp_no" +
                "WHERE d.dept_no = 'D005';");
        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-10s", columns);
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void Test35() throws SQLException {
        //  35- Finding the managerial history of employee with ID (emp. no) 110022
        DBConnectionOpen();
        List<List<String>> returnedData = getListData(
                "SELECT employees.first_name, employees.last_name, dept_manager.from_date, dept_manager.to_date\n" +
                        "FROM employees\n" +
                        "JOIN dept_manager ON employees.emp_no = dept_manager.emp_no\n" +
                        "WHERE employees.emp_no = '110022';");

        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-10s", columns);
            }
            System.out.println();
        }

        DBConnectionClose();
    }

    @Test
    public void Test20() throws SQLException {
        // 20- List the names, last names, hire dates, and salaries of all employees in the Sales department who
        // were hired between January 01, 1985 and December 31, 1989, sorted by salary in descending order.
        DBConnectionOpen();
        List<List<String>> returnedData = getListData(
                "SELECT employees.first_name, employees.last_name, dept_manager.from_date, dept_manager.to_date\n" +
                        "FROM employees\n" +
                        "JOIN dept_manager ON employees.emp_no = dept_manager.emp_no\n" +
                        "WHERE employees.emp_no = '110022';");

        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-10s", columns);
            }
            System.out.println();
        }

        DBConnectionClose();
    }

    @Test
    public void Test28() throws SQLException {
        // 28. List all employees hired after '1994-02-24' and earning more than 50,000
        DBConnectionOpen();
        List<List<String>> returnedData = getListData(
                "SELECT e.emp_no, e.first_name, e.last_name, e.birth_date, e.gender, e.hire_date, t.title, s.salary\n" +
                        "FROM employees e\n" +
                        "JOIN titles t ON e.emp_no = t.emp_no\n" +
                        "JOIN salaries s ON e.emp_no = s.emp_no\n" +
                        "WHERE e.hire_date > '1994-02-24' AND s.salary > 50000;\n");
        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-10s", columns);
            }
            System.out.println();
        }

        DBConnectionClose();
    }
}


























