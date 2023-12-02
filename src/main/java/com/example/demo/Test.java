package com.example.demo;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseUtil.makeConnection();
        PreparedStatement ps = con.prepareStatement("select * from board");
        ResultSet rs = ps.executeQuery();

//        while (rs.absolute(1)){
//            System.out.println(rs.getString("id"));
//        }
        rs.absolute(1);
        System.out.println();
    }
}
