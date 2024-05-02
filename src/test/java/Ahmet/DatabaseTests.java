package Ahmet;

import org.testng.annotations.Test;
import utilities.DatabaseHelper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class DatabaseTests extends DatabaseHelper {

    @Test
    public void Test10() throws SQLException {
        DBConnectionOpen();

        ResultSet rs = queryScreen.executeQuery("SELECT emp_no, salary, from_date, to_date\n" +
                "FROM salaries\n" +
                "WHERE emp_no = '10102'\n" +
                "ORDER BY from_date;");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");

        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rs.getString(i) + "\t");

            }
            System.out.println();
        }


        DBConnectionClose();

    }

    @Test
    public void Test11() throws SQLException {

        DBConnectionOpen();

        ResultSet rs = queryScreen.executeQuery("SELECT emp_no, salary, to_date\n" +
                "FROM salaries\n" +
                "WHERE emp_no = '10102'\n" +
                "ORDER BY to_date;");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }


        DBConnectionClose();

    }
    @Test
    public void Test12() throws SQLException {

        DBConnectionOpen();

        ResultSet rs = queryScreen.executeQuery("SELECT * FROM employees\n" +
                "JOIN salaries ON employees.emp_no = salaries.emp_no\n" +
                "ORDER BY salary DESC\n" +
                "LIMIT 1;");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }


        DBConnectionClose();

    }
    @Test
    public void Test13() throws SQLException {

        DBConnectionOpen();

        ResultSet rs = queryScreen.executeQuery("SELECT emp_no, salary, to_date\n" +
                "FROM salaries\n" +
                "WHERE (emp_no, to_date) IN (SELECT emp_no, MAX(to_date) FROM salaries GROUP BY emp_no);");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }


        DBConnectionClose();

    }
    @Test
    public void Test23() throws SQLException {

        DBConnectionOpen();

        ResultSet rs = queryScreen.executeQuery("SELECT de.dept_no, COUNT(*) AS employee_count\n" +
                "FROM dept_emp de\n" +
                "GROUP BY de.dept_no;");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }


        DBConnectionClose();

    }

    @Test
    public void Test25() throws SQLException {

        DBConnectionOpen();

        ResultSet rs = queryScreen.executeQuery("SELECT *\n" +
                "FROM employees\n" +
                "WHERE first_name = 'Annemarie' AND last_name = 'Redmiles';");
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }


        DBConnectionClose();

    }


}
