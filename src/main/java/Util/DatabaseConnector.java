package Util;

import Model.Address;
import Model.Person;
import Model.Therapist;
import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class DatabaseConnector {
    private static String port= "3306";
    private static String name = "ehms";
    private static String user = "root";
    private static String pass = "";
    private static String url = "jdbc:mysql://localhost:"+port+"/"+name;
    private static Connection con = null;

    static {
        try {
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected successfully");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Denied");
        }

    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        DatabaseConnector.url = url;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        DatabaseConnector.user = user;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        DatabaseConnector.pass = pass;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        DatabaseConnector.port = port;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        DatabaseConnector.name = name;
    }

    public static Connection getConnection() {
        return con;
    }


}
