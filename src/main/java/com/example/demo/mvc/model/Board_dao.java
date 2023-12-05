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

    public void insertBoard(Board_dto board){
        String id = board.getId();
        String name = board.getName();
        String content = board.getContent();
        String subject = board.getSubject();
        String regist_day = board.getRegist_day();
        int hit = board.getHit();
        String ip = board.getIp();

        Connection con = null;
        PreparedStatement ps = null;

        try{
            con = Db_connection.getconnection();
            String sql = "insert into board(name, content, subject, regist_day, hit, ip, id) values(?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, content);
            ps.setString(3, subject);
            ps.setString(4, regist_day);
            ps.setInt(5, hit);
            ps.setString(6, ip);
            ps.setString(7, id);

            int result = ps.executeUpdate();
            if (result == 0) {
                throw new SQLException("insert를 실행했지만 삽입되지 않았습니다");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Board_dto getBoardByNum(int num) {
        increaseHit(num);
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            con = Db_connection.getconnection();
            String sql = "select * from board where num = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, num);
            rs = ps.executeQuery();
            if (rs.next()) {
                Board_dto board = new Board_dto();
                board.setId(rs.getString("id"));
                board.setIp(rs.getString("ip"));
                board.setHit(rs.getInt("hit"));
                board.setNum(rs.getInt("num"));
                board.setSubject(rs.getString("subject"));
                board.setContent(rs.getString("content"));
                board.setRegist_day(rs.getString("regist_day"));
                board.setName(rs.getString("name"));

                return board;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void increaseHit(int num) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Db_connection.getconnection();
            String sql = "select hit from board where num = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, num);
            rs = ps.executeQuery();

            int hit = 0;
            if (rs.next()) {
                hit = rs.getInt("hit") + 1;
            }
            String sql2 = "update board set hit = ? where num = ?";
            ps = con.prepareStatement(sql2);
            ps.setInt(1, hit);
            ps.setInt(2, num);
            int result = ps.executeUpdate();
            if (result == 0) {
                throw new RuntimeException("update가 실행되지 않았습니다 sql 문에 오류가 있습니다");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateBoard(Board_dto board) {
        try {
            Connection con = Db_connection.getconnection();
            String sql = "update board set name = ?, subject = ?, content = ?, regist_day = ? where num = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, board.getName());
            ps.setString(2, board.getSubject());
            ps.setString(3, board.getContent());
            ps.setString(4, board.getRegist_day());
            ps.setInt(5, board.getNum());

            int result = ps.executeUpdate();
            if (result == 0) {
                throw new SQLException("update 되지 않았습니다 sql 문에 오류가 있습니다");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteBoard(int num) {
        try {
            Connection con = Db_connection.getconnection();
            String sql = "delete from board where num = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, num);
            int result = ps.executeUpdate();
            if (result == 0) {
                throw new SQLException("sql문이 실행되었지만 반영되지 않았습니다");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
