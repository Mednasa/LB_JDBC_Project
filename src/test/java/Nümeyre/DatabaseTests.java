package Nümeyre;

import org.testng.annotations.Test;
import utilities.DatabaseHelper;

import java.sql.*;
import java.util.List;

public class DatabaseTests extends DatabaseHelper {


    @Test
    public void Test7()  {


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
    public void Test34()  {

        DBConnectionOpen();
        List<List<String>> returnedData = getListData(
                "SELECT dept_no, COUNT(*) AS employee_count\n" +
                        "FROM dept_emp\n" +
                        "GROUP BY dept_no");


        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-10s", columns);
            }
            System.out.println();
        }

        DBConnectionClose();

    }

    @Test
    public void Test27()  {


        DBConnectionOpen();
        List<List<String>> returnedData = getListData("select first_name,last_name,employees.emp_no,dept_emp.dept_no,title \n" +
                "from employees\n" +
                "left join dept_emp ON employees.emp_no=dept_emp.emp_no\n" +
                "left join titles ON dept_emp.emp_no=titles.emp_no\n" +
                "where dept_emp.dept_no='d005'");


        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-10s", columns);
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void Test35()  {

        DBConnectionOpen();
        List<List<String>> returnedData = getListData(
                "SELECT employees.first_name, employees.last_name, dept_manager.from_date, dept_manager.to_date\n" +
                        "FROM employees\n" +
                        "JOIN dept_manager ON employees.emp_no = dept_manager.emp_no\n" +
                        "WHERE employees.emp_no = '110022'");

        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-10s", columns);
            }
            System.out.println();
        }

        DBConnectionClose();
    }

    @Test
    public void Test20()  {

        DBConnectionOpen();
        List<List<String>> returnedData = getListData(
                "SELECT first_name, last_name, hire_date, salaries.salary\n" +
                        "FROM employees\n" +
                        "WHERE hire_date BETWEEN '1985-01-01' AND '1989-12-31'\n" +
                        "AND department = 'Sales'\n" +
                        "AND emp_no IN (SELECT emp_no FROM salaries)\n" +
                        "ORDER BY salaries.salary DESC");

        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-10s", columns);
            }
            System.out.println();
        }

        DBConnectionClose();
    }

    @Test
    public void Test28()  {

        DBConnectionOpen();
        List<List<String>> returnedData = getListData(
                "SELECT e.emp_no, e.first_name, e.last_name, e.birth_date, e.gender, e.hire_date, t.title, s.salary\n" +
                        "FROM employees e\n" +
                        "JOIN titles t ON e.emp_no = t.emp_no\n" +
                        "JOIN salaries s ON e.emp_no = s.emp_no\n" +
                        "WHERE e.hire_date > '1994-02-24' AND s.salary > 50000");


        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-10s", columns);
            }
            System.out.println();
        }

        DBConnectionClose();
    }
}


























