package Onur;

import org.testng.annotations.Test;
import utilities.DatabaseHelper;

import java.util.List;

public class DatabaseTests extends DatabaseHelper {

    @Test
    public void Test15() {

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
    public void Test17() {

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
    public void Test19() {

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


}
