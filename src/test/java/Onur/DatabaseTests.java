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


}
