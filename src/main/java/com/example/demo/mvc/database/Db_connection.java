package com.example.demo.mvc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db_connection {
    public static Connection getconnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/shoppingmall?characterEncoding=utf8";
        String user ="root";
        String password = "1234";

        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, user, password);

        return con;
    }
}
