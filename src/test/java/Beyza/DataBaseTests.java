package Beyza;

import org.testng.annotations.Test;
import utilities.DatabaseHelper;

import java.sql.ResultSet;
import java.util.List;

public class DataBaseTests extends DatabaseHelper {
    @Test
    public void Test29() {
        DBConnectionOpen();

        List<List<String>> returnedData = getListData("SELECT e.emp_no, e.first_name, e.last_name, e.birth_date, e.gender, e.hire_date\n" +
                "FROM employees e\n" +
                "JOIN dept_manager dm ON e.emp_no = dm.emp_no\n" +
                "JOIN departments d ON dm.dept_no = d.dept_no\n" +
                "JOIN titles t ON e.emp_no = t.emp_no\n" +
                "WHERE d.dept_name = 'Sales' AND t.title = 'Manager';");


        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-20s|", columns);
            }
            System.out.println();
        }
        DBConnectionClose();
    }


    @Test
    public void Test30() {
        DBConnectionOpen();

        List<List<String>> returnedData = getListData("SELECT employees.emp_no, departments.dept_name, DATEDIFF(MAX(dept_emp.to_date), MIN(dept_emp.from_date)) AS work_duration\n" +
                "FROM employees\n" +
                "JOIN dept_emp ON employees.emp_no = dept_emp.emp_no\n" +
                "JOIN departments ON dept_emp.dept_no = departments.dept_no\n" +
                "GROUP BY employees.emp_no\n" +
                "ORDER BY work_duration DESC\n" +
                "LIMIT 1;");


        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-20s|", columns);
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void Test31() {
        DBConnectionOpen();

        List<List<String>> returnedData = getListData("SELECT employees.first_name, employees.last_name, dept_emp.dept_no, salaries.salary\n" +
                "FROM employees\n" +
                "JOIN salaries ON employees.emp_no = salaries.emp_no\n" +
                "JOIN dept_emp ON employees.emp_no = dept_emp.emp_no\n" +
                "WHERE salaries.salary = (\n" +
                "    SELECT max(salaries.salary) as max_salary\n" +
                "    FROM employees\n" +
                "    JOIN salaries ON employees.emp_no = salaries.emp_no\n" +
                "    JOIN dept_emp ON employees.emp_no = dept_emp.emp_no\n" +
                "    WHERE dept_emp.dept_no = 'D004'\n" +
                ") AND dept_emp.dept_no = 'D004';");


        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-20s|", columns);
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void Test32() {
        DBConnectionOpen();

        List<List<String>> returnedData = getListData("Select emp_no, title, from_date, to_date\n" +
                "From titles\n" +
                "Where emp_no = '10102'\n" +
                "Order by from_date;");


        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-20s|", columns);
            }
            System.out.println();
        }
        DBConnectionClose();
    }


    @Test
    public void Test33() {
        DBConnectionOpen();

        List<List<String>> returnedData = getListData("SELECT AVG(DATEDIFF(CURRENT_DATE, birth_date) / 365) AS avg_age FROM employees;");

        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-20s|", columns);
            }
            System.out.println();
        }
        DBConnectionClose();
    }


}