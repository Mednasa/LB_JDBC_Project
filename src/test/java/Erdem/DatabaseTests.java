package Erdem;

import org.testng.annotations.Test;
import utilities.DatabaseHelper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DatabaseTests extends DatabaseHelper {

    @Test
    public void databaseTest41() throws SQLException {
        DBConnectionOpen();

        ResultSet resultSet = queryScreen.executeQuery("SELECT \n" +
                "    first_name, last_name, dept_name\n" +
                "FROM\n" +
                "    employees\n" +
                "        LEFT JOIN\n" +
                "    dept_emp ON dept_emp.emp_no = employees.emp_no\n" +
                "        LEFT JOIN\n" +
                "    departments ON dept_emp.dept_no = departments.dept_no\n" +
                "WHERE\n" +
                "    employees.emp_no = 10102;");

        ResultSetMetaData rsmd = resultSet.getMetaData();


        System.out.println();
        boolean isTrue = true;
        while (resultSet.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                if (isTrue) {
                    System.out.printf("%-10s", rsmd.getColumnName(i) + "\t");
                } else {
                    System.out.printf("%-9s", resultSet.getString(i) + "\t");
                }
            }
            if (isTrue) {
                resultSet.previous();
            }
            isTrue = false;
            System.out.println();
        }

        DBConnectionClose();
    }


    @Test
    public void dataBaseTest40() throws SQLException {
        DBConnectionOpen();

        ResultSet resultSet = queryScreen.executeQuery("select * from employees where hire_date like " +
                "\"1992-04%\" group by emp_no order by emp_no;\n");

        ResultSetMetaData rsmd = resultSet.getMetaData();


        System.out.println();
        boolean isTrue = true;
        while (resultSet.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                if (isTrue) {
                    System.out.printf("%-13s|", rsmd.getColumnName(i));
                } else {
                    System.out.printf("%-13s|", resultSet.getString(i));
                }
            }
            if (isTrue) {
                resultSet.previous();
            }
            isTrue = false;
            System.out.println();
        }

        DBConnectionClose();

    }

    @Test
    public void dataBaseTest39() throws SQLException {
        DBConnectionOpen();

        ResultSet resultSet = queryScreen.executeQuery("select * from employees order by birth_date limit 100;\n");


        ResultSetMetaData rsmd = resultSet.getMetaData();


        System.out.println();
        boolean isTrue = true;
        while (resultSet.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                if (isTrue) {
                    System.out.printf("%-13s|", rsmd.getColumnName(i));
                } else {
                    System.out.printf("%-13s|", resultSet.getString(i));
                }
            }
            if (isTrue) {
                resultSet.previous();
            }
            isTrue = false;
            System.out.println();
        }

        DBConnectionClose();

    }


    @Test
    public void dataBaseTest38() throws SQLException {
        DBConnectionOpen();

        ResultSet resultSet = queryScreen.executeQuery("SELECT employees.first_name, employees.last_name\n" +
                "FROM employees\n" +
                "left JOIN dept_manager ON employees.emp_no = dept_manager.emp_no\n" +
                "WHERE dept_manager.dept_no = 'D005';");

        ResultSetMetaData rsmd = resultSet.getMetaData();

        System.out.println();
        boolean isTrue = true;
        while (resultSet.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                if (isTrue) {
                    System.out.printf("%-13s|", rsmd.getColumnName(i));
                } else {
                    System.out.printf("%-13s|", resultSet.getString(i));
                }
            }
            if (isTrue) {
                resultSet.previous();
            }
            isTrue = false;
            System.out.println();
        }

        DBConnectionClose();
    }

    @Test
    public void dataBaseTest37() throws SQLException {
        DBConnectionOpen();

        ResultSet resultSet = queryScreen.executeQuery(
                "SELECT employees.emp_no, employees.first_name, " +
                        "employees.last_name, titles.title\n" +
                        "FROM employees\n" +
                        "JOIN titles ON employees.emp_no = titles.emp_no\n" +
                        "WHERE titles.to_date = (SELECT MAX(to_date) FROM titles WHERE titles.emp_no = employees.emp_no) limit 100;"
        );

        ResultSetMetaData rsmd = resultSet.getMetaData();

        System.out.println();
        boolean isTrue = true;
        while (resultSet.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                if (isTrue) {
                    System.out.printf("%-18s|", rsmd.getColumnName(i));
                } else {
                    System.out.printf("%-18s|", resultSet.getString(i));
                }
            }
            if (isTrue) {
                resultSet.previous();
            }
            isTrue = false;
            System.out.println();
        }

        DBConnectionClose();
    }

    @Test
    public void dataBaseTest36() throws SQLException {
        DBConnectionOpen();

        ResultSet resultSet = queryScreen.executeQuery(
                "SELECT \n" +
                        "emp_no, LEFT(to_date, 4) - LEFT(from_date, 4) AS employment_duration\n" +
                        "FROM\n" +
                        "    dept_emp\n" +
                        "WHERE\n" +
                        "    to_date < '9999-01-01'\n" +
                        "GROUP BY emp_no\n" +
                        "ORDER BY employment_duration DESC limit 100"
        );

        ResultSetMetaData rsmd = resultSet.getMetaData();

        System.out.println();
        boolean isTrue = true;
        while (resultSet.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                if (isTrue) {
                    System.out.printf("%-19s|", rsmd.getColumnName(i));
                } else {
                    System.out.printf("%-19s|", resultSet.getString(i));
                }
            }
            if (isTrue) {
                resultSet.previous();
            }
            isTrue = false;
            System.out.println();
        }

        DBConnectionClose();
    }


}
