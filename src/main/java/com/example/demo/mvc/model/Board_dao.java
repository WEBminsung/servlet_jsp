package com.example.demo.mvc.model;

import com.example.demo.mvc.database.Db_connection;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class Board_dao {
    private static Board_dao instance;
    private Board_dao(){}
    public static Board_dao getInstance(){
        if (instance == null){
            instance = new Board_dao();
        }
        return instance;
    }
    // board 테이블의 레코드 개수
    public int getListCount(String items, String text){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int x = 0;
        try {
            con = Db_connection.getconnection();
            String sql;
            if (items == null && text == null) {
                sql = "select count(*)" +
                        "from board";
            } else {
                sql = "select count(*) " +
                        "from board" +
                        "where" + items + " like '%" + text + "%'";
            }
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if(rs.next()){
                x = rs.getInt(1);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    con.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return x;
    }
    public ArrayList<Board_dto> getBoardList(int page, int limit, String items, String text){
        Connection con = null;
        Statement ps = null;
        ResultSet rs = null;

        int start = (page - 1) * limit;
        int index = start + 1;
        int maxIndex = getListCount(items, text);

        String sql;
        if(text == null && items == null){
            sql = "select * from board";
        }else{
            sql = "select * from board where " + items + " like '%" + text + "%'";
        }


        ArrayList<Board_dto> list = new ArrayList<>();
        try {
            con = Db_connection.getconnection();
            ps = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery(sql);
            while (rs.absolute(index)){
                Board_dto boardDto = new Board_dto();
                boardDto.setNum(rs.getInt("num"));
                boardDto.setId(rs.getString("id"));
                boardDto.setName(rs.getString("name"));
                boardDto.setSubject(rs.getString("subject"));
                boardDto.setContent(rs.getString("content"));
                boardDto.setRegist_day(rs.getString("regist_day"));
                boardDto.setHit(rs.getInt("hit"));
                boardDto.setIp(rs.getString("ip"));
                list.add(boardDto);
                if (index < (start + limit) && index <= maxIndex){
                    index += 1;
                }else{
                    break;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }finally{
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    public String getLoginNameById(String id){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String name  = null;
        try {
            con = Db_connection.getconnection();
            String sql = "select * from member where id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();


            if (rs.next()) {
                name =  rs.getString("name");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }finally{
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return name;
    }

}
