package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static Connection con = null;
    static
    {
        String url="jdbc:mysql://localhost:3306/[TODO add Databse]"; //TODO
        String user = "root";
        String pass = "";
        try {
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected successfully");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Denied");
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
