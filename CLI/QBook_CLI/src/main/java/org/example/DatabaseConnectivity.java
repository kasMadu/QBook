package org.example;

import java.sql.*;
public class DatabaseConnectivity {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/qbook";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Password";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection failed.");
        }
    }
}
