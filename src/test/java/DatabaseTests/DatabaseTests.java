package DatabaseTests;

import org.testng.annotations.Test;
import utilities.DatabaseHelper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class DatabaseTests extends DatabaseHelper {

    @Test
    public void dataBaseTest1() throws SQLException {

        DBConnectionOpen();
        ResultSet rs = queryScreen.executeQuery("SELECT *\n" +
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
    public void dataBaseTest2() throws SQLException {

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

    @Test
    public void dataBaseTest3() throws SQLException {

        DBConnectionOpen();
        ResultSet rs=queryScreen.executeQuery("SELECT AVG(salary) AS average_salary\n" +
                "FROM salaries");
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
    public void dataBaseTest4() throws SQLException {

        DBConnectionOpen();
        ResultSet rs=queryScreen.executeQuery("SELECT AVG(s.salary) AS average_salary\n" +
                "FROM employees e\n" +
                "INNER JOIN salaries s ON e.emp_no = s.emp_no\n" +
                "WHERE e.gender = 'F'");

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
    public void dataBaseTest5() throws SQLException {

        DBConnectionOpen();
        ResultSet rs=queryScreen.executeQuery("SELECT AVG(s.salary) AS average_salary\n" +
                "FROM employees e\n" +
                "INNER JOIN salaries s ON e.emp_no = s.emp_no\n" +
                "WHERE e.gender = 'M'");

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
    public void dataBaseTest6() throws SQLException {

        DBConnectionOpen();
        ResultSet rs=queryScreen.executeQuery("SELECT e.*, s.salary\n" +
                "FROM employees e\n" +
                "JOIN dept_emp de ON e.emp_no = de.emp_no\n" +
                "JOIN departments d ON de.dept_no = d.dept_no\n" +
                "JOIN salaries s ON e.emp_no = s.emp_no\n" +
                "WHERE d.dept_name = 'Sales' AND s.salary > 70000");

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
    public void dataBaseTest7(){

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
    public void dataBaseTest8() throws SQLException {

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
    public void dataBaseTest9() throws SQLException {

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
    public void dataBaseTest10() throws SQLException {

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
    public void dataBaseTest11() throws SQLException {

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
    public void dataBaseTest12() throws SQLException {

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
    public void dataBaseTest13() throws SQLException {

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
    public void dataBaseTest14() throws SQLException {

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
    public void dataBaseTest15() {

        DBConnectionOpen();
        List<List<String>> returnedData = getListData("select first_name,last_name,departments.dept_name,avg(salary) as Maas \n" +
                "from salaries\n" +
                "left join employees ON employees.emp_no=salaries.emp_no\n" +
                "left join dept_emp ON dept_emp.emp_no=salaries.emp_no\n" +
                "left join departments ON departments.dept_no=dept_emp.dept_no\n" +
                "where dept_name='Research'\n" +
                "group by employees.emp_no\n" +
                "order by Maas desc limit 1 ;");

        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-10s",columns);
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void dataBaseTest16() {

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
    public void dataBaseTest17() {

        DBConnectionOpen();
        List<List<String>> returnedData = getListData("select dept_name, first_name, last_name, average_salary\n" +
                "from (\n" +
                "    select d.dept_name, e.first_name, e.last_name, AVG(s.salary) as average_salary\n" +
                "    from departments d\n" +
                "    left join dept_emp de ON d.dept_no = de.dept_no\n" +
                "\tleft join employees e ON e.emp_no = de.emp_no\n" +
                "\tleft join salaries s ON s.emp_no = e.emp_no\n" +
                "    group by  e.emp_no\n" +
                "    order by average_salary desc\n" +
                ") as new_table\n" +
                "group by dept_name\n" +
                "order by MAX(average_salary) desc;");


        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-20s|",columns);
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void dataBaseTest18() {

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
    public void dataBaseTest19() {

        DBConnectionOpen();
        List<List<String>> returnedData = getListData("select first_name,last_name,salaries.from_date as İşe_Başlama_Tarihi \n" +
                "from salaries\n" +
                "left join employees ON employees.emp_no=salaries.emp_no\n" +
                "left join dept_emp ON dept_emp.emp_no=salaries.emp_no\n" +
                "left join departments ON departments.dept_no=dept_emp.dept_no\n" +
                "where salaries.from_date between'1985-01-01' and '1989-12-31'\n" +
                "group by employees.emp_no\n" +
                "order by salaries.from_date,employees.first_name");


        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < returnedData.get(i).size(); j++) {
                System.out.printf("%-14s|",returnedData.get(i).get(j));

            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void dataBaseTest20() {

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
    public void dataBaseTest21() throws SQLException {

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

    @Test
    public void dataBaseTest22() {

        DBConnectionOpen();
        List<List<String>> returnedData1 = getListData("select count(distinct first_name)as miktar   \n" +
                "from employees");

        System.out.println("Unique First Names Numbers");
        for (List<String> row : returnedData1) {
            for (String columns : row) {
                System.out.printf("%-5s",columns);
            }
            System.out.println();
        }


        List<List<String>> returnedData2 = getListData("select count(distinct dept_name) as miktar\n" +
                "from departments");

        System.out.println("The Number Of Distinct Department Names");
        for (List<String> row : returnedData2) {
            for (String columns : row) {
                System.out.printf("%-5s",columns);
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void dataBaseTest23() throws SQLException {

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
    public void dataBaseTest24() {

        DBConnectionOpen();
        List<List<String>> returnedData = getListData("select  first_name,last_name,hire_date as iseAlınmaTarihi \n" +
                "from employees\n" +
                "where hire_date between '1985-01-01'and '1990-02-21'\n" +
                "order by iseAlınmaTarihi limit 100");

        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-15s|",columns);
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void dataBaseTest25() throws SQLException {

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

    @Test
    public void dataBaseTest26() {

        DBConnectionOpen();
        List<List<String>> returnedData = getListData("select distinct employees.emp_no as calisanNumarası,employees.birth_date as dogumTarihi, \n" +
                "employees.first_name as adi,employees.last_name as soyadi,employees.gender as cinsiyet, employees.hire_date as iseAlınmaTarihi,\n" +
                "salaries.from_date,salaries.to_date,salaries.salary as maas, departments.dept_name as departmanAdi, titles.title as unvan\n" +
                "from employees\n" +
                "left join titles ON employees.emp_no=titles.emp_no\n" +
                "left join salaries ON titles.emp_no=salaries.emp_no\n" +
                "left join dept_emp ON salaries.emp_no=dept_emp.emp_no\n" +
                "left join departments ON dept_emp.dept_no=departments.dept_no\n" +
                "where   first_name='Annemarie' and last_name='Redmiles';");

        for (List<String> row : returnedData) {
            for (String columns : row) {
                System.out.printf("%-13s|",columns);
            }
            System.out.println();
        }
        DBConnectionClose();
    }

    @Test
    public void dataBaseTest27() {

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
    public void dataBaseTest28() throws SQLException {

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

    @Test
    public void dataBaseTest29() {

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
    public void dataBaseTest30() {

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
    public void dataBaseTest31() {

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
    public void dataBaseTest32() {

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
    public void dataBaseTest33() {

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

    @Test
    public void dataBaseTest34() {

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
    public void dataBaseTest35() {

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
    public void dataBaseTest41() throws SQLException {

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

}
