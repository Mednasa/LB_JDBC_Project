package Nigar;

import org.testng.annotations.Test;
import utilities.DatabaseHelper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


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
            System.out.printf("%-10s",rsmd.getColumnName(i) + "\t");
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.printf("%-10s",rs.getString(i) + "\t");
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
            System.out.printf("%-20s", rsmd2.getColumnName(i) + "\t");
        }
        System.out.println();

        while (rs2.next()) {
            for (int i = 1; i <= rsmd2.getColumnCount(); i++) {
                System.out.printf( "%-20s", rs2.getString(i) + "\t");
            }
            System.out.println();
        }
        DBConnectionClose();
    }

}
