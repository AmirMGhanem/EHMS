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

    private static Connection con = null;

    static {
        String url = "jdbc:mysql://localhost:3306/ehms";
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
