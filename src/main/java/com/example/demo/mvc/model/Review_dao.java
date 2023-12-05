package com.example.demo.mvc.model;

import com.example.demo.mvc.database.Db_connection;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Review_dao {
    public static Review_dao instance;
    private Review_dao(){

    }
    public static Review_dao getInstance() {
        if (instance == null) {
            instance = new Review_dao();
        }
        return instance;
    }

    public ArrayList<Review_dto> getReviewList(String id) {
        try {
            Connection con = Db_connection.getconnection();
            String sql = "select * from review where product_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Review_dto> reviews = new ArrayList<>();
            while (rs.next()) {
                Review_dto review = new Review_dto();
                review.setId(rs.getString("id"));
                review.setNum(rs.getInt("num"));
                review.setContent(rs.getString("content"));
                review.setFilename(rs.getString("filename"));
                review.setScore(rs.getInt("score"));
                review.setRegist_day(rs.getString("regist_day"));
                review.setProductId(rs.getString("product_id"));
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void addReview(Review_dto review) {
        try {
            Connection con = Db_connection.getconnection();
            String sql = "insert into review (id, content, filename, score, regist_day, product_id, num) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, review.getId());
            ps.setString(2, review.getContent());
            ps.setString(3, review.getFilename());
            ps.setInt(4, review.getScore());
            ps.setString(5, review.getRegist_day());
            ps.setString(6, review.getProductId());
            ps.setInt(7, review.getNum());

            int result = ps.executeUpdate();
            if (result == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int num, String productId) {

        try {
            Connection con = Db_connection.getconnection();
            String sql = "delete from review where num = ? and product_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, num);
            ps.setString(2, productId);
            int result = ps.executeUpdate();
            if (result == 0) {
                throw new SQLException();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
