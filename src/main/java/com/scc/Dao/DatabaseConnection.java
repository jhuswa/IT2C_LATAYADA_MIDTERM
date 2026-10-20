package com.scc.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Replace this with the exact folder path where your SQLiteStudio database is saved
    private static final String URL = "jdbc:sqlite:canteen.db";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL);
    }
}