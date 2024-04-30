package utilities;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    public static Connection link;
    public static Statement queryScreen;

    public static List<List<String>> getListData(String query) {
        List<List<String>> table = new ArrayList<>();

        try {
            DBConnectionOpen();
            ResultSet rs = queryScreen.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {

                ArrayList<String> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++)
                    row.add(rs.getString(i));


                table.add(row);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            DBConnectionClose();
        }

        return table;
    }

    public static void DBConnectionOpen() {
        String url = ConfigReader.getProperty("URL");
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        try {
            link = DriverManager.getConnection(url, username, password);
            queryScreen = link.createStatement();
        } catch (Exception ex) {
            System.out.println("ex.getMessage() = " + ex.getMessage());
        }
    }

    public static void DBConnectionClose() {
        try {
            link.close();
        } catch (SQLException e) {
            System.out.println("ex.getMessage() = " + e.getMessage());
        }
    }
}
