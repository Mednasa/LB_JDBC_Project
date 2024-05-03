package OmerFaruk;

import org.testng.annotations.Test;
import utilities.DatabaseHelper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DatabaseTests extends DatabaseHelper {
    @Test
    public void Test1() throws SQLException, SQLException {
        //  1. List all employees in department '`D001`'.

        DBConnectionOpen();

        ResultSet rs=queryScreen.executeQuery("SELECT *\n" +
                "FROM employees\n" +
                "INNER JOIN dept_emp ON employees.emp_no = dept_emp.emp_no\n" +
                "WHERE dept_no = 'D001'");
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
    public void Test2() throws SQLException {
        // 2. List all employees in '`Human Resources`' department.

        DBConnectionOpen();

        ResultSet rs = queryScreen.executeQuery("SELECT employees.*\n" +
                "FROM employees\n" +
                "INNER JOIN dept_emp ON employees.emp_no = dept_emp.emp_no\n" +
                "INNER JOIN departments ON dept_emp.dept_no = departments.dept_no\n" +
                "WHERE departments.dept_name = 'Human Resources'");
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
