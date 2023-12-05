package com.example.demo.mvc.model;

import com.example.demo.mvc.database.Db_connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Product_dao {

    private static Product_dao instance = new Product_dao();

    public static Product_dao getInstance() {
        return instance;
    }

    public ArrayList<Product> findAll() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = Db_connection.getconnection();
            String sql = "SELECT * FROM product";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Product> productArrayList = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("p_id"));
                product.setDescription(rs.getString("p_description"));
                product.setName(rs.getString("p_name"));
                product.setUnitPrice(rs.getInt("p_unit_price"));
                product.setManufacturer(rs.getString("p_manufacturer"));
                product.setCategory(rs.getString("p_category"));
                product.setUnitsInStock(rs.getInt("p_units_in_stock"));
                product.setCondition(rs.getString("p_condition"));
                product.setFilename(rs.getString("p_filename"));
                product.setQuantity(rs.getInt("p_quantity"));
                productArrayList.add(product);
            }
            return productArrayList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Product findById(String product_id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = Db_connection.getconnection();
            String sql = "SELECT * FROM product WHERE p_id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, product_id);
            rs = ps.executeQuery();

            ps.setString(1, product_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("p_id"));
                product.setDescription(rs.getString("p_description"));
                product.setName(rs.getString("p_name"));
                product.setUnitPrice(rs.getInt("p_unit_price"));
                product.setManufacturer(rs.getString("p_manufacturer"));
                product.setCategory(rs.getString("p_category"));
                product.setUnitsInStock(rs.getInt("p_units_in_stock"));
                product.setCondition(rs.getString("p_condition"));
                product.setFilename(rs.getString("p_filename"));
                product.setQuantity(rs.getInt("p_quantity"));
                return product;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void productAdd(Product product) {
        String id = product.getId();
        String name = product.getName();
        int unitPrice = product.getUnitPrice();
        String description = product.getDescription();
        String manufacturer = product.getManufacturer();
        String category = product.getCategory();
        long unitsInStock = product.getUnitsInStock();
        String condition = product.getCondition();
        String filename = product.getFilename();
        int quantity = product.getQuantity();


        try {
            Connection con = Db_connection.getconnection();
            String sql = "INSERT INTO product VALUE(?, ?, ?, ?, ?, ?, ? ,? ,? ,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setInt(3, unitPrice);
            ps.setString(4, description);
            ps.setString(5, manufacturer);
            ps.setString(6, category);
            ps.setLong(7, unitsInStock);
            ps.setString(8, condition);
            ps.setString(9, filename);
            ps.setInt(10, quantity);

            int result = ps.executeUpdate();

            if (result > 0) {

            } else {
                throw new SQLException("INSERT 문이 실행 되었지만 아무 일도 일어나지 않았습니다");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void productUpdate(Product product) {
        String id = product.getId();
        String name = product.getName();
        int unitPrice = product.getUnitPrice();
        String description = product.getDescription();
        String manufacturer = product.getManufacturer();
        String category = product.getCategory();
        long unitsInStock = product.getUnitsInStock();
        String condition = product.getCondition();
        String filename = product.getFilename();
        int quantity = product.getQuantity();

        try {
            Connection con = Db_connection.getconnection();
            String sql = "update product set " +
                    "p_name = ?, p_unit_price = ?, p_description = ?, p_manufacturer = ?, p_category = ?, p_units_in_stock = ?, p_condition = ? where p_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, unitPrice);
            ps.setString(3, description);
            ps.setString(4, manufacturer);
            ps.setString(5, category);
            ps.setLong(6, unitsInStock);
            ps.setString(7, condition);
            ps.setString(8, id);

            int result = ps.executeUpdate();

            if (result > 0) {

            } else {
                throw new SQLException("INSERT 문이 실행 되었지만 아무 일도 일어나지 않았습니다");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void productDelete(String id) {
        try {
            Connection con = Db_connection.getconnection();
            String sql = "delete from product where p_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            int rs = ps.executeUpdate();
            if (rs > 0) {

            } else {
                throw new SQLException("sql 문이 실행되었지만 어떤 작업도 수행되지 않았습니다");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
