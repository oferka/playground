package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");
        PreparedStatement ps = connection.prepareStatement("CREATE DATABASE db_example");
        int result = ps.executeUpdate();
    }
}
