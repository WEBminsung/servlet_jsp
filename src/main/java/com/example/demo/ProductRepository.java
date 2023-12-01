package com.example.demo;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class ProductRepository {

    private static ProductRepository instance = new ProductRepository();

    public static ProductRepository getInstance() {
        return instance;
    }

    public ArrayList<Product> findAll() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseUtil.makeConnection();
            String sql = "SELECT * FROM product";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            return DatabaseUtil.processResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtil.close(con, ps, rs);
        }
    }
    public Product findById(String product_id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseUtil.makeConnection();
            String sql = "SELECT * FROM product WHERE p_id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, product_id);
            rs = ps.executeQuery();

            ps.setString(1, product_id);
            ArrayList<Product> productArrayList = DatabaseUtil.processResultSet(rs);

            if (!productArrayList.isEmpty()) {
                return productArrayList.get(0);
            } else {
                throw new SQLException("찾는 아이디의 상품이 데이터베이스에 존재하지 않아요.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtil.close(con, ps, rs);
        }
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
            Connection con = DatabaseUtil.makeConnection();
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

            if(result > 0){

            }else {
                throw new SQLException("INSERT 문이 실행 되었지만 아무 일도 일어나지 않았습니다");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void productUpdate(Product product){
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
            Connection con = DatabaseUtil.makeConnection();
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

            if(result > 0){

            }else {
                throw new SQLException("INSERT 문이 실행 되었지만 아무 일도 일어나지 않았습니다");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void productDelete(String id){
        try {
            Connection con = DatabaseUtil.makeConnection();
            String sql = "delete from product where p_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            int rs = ps.executeUpdate();
            if(rs > 0){

            }else{
                throw new SQLException("sql 문이 실행되었지만 어떤 작업도 수행되지 않았습니다");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
class DatabaseUtil{
    static public Connection makeConnection(){
        String jdbcURL = "jdbc:mysql://localhost:3306/shoppingmall";
        String user = "root";
        String password = "1234";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    static public ArrayList<Product> processResultSet(ResultSet rs) throws SQLException {
        ArrayList<Product> productArrayList = new ArrayList<>();
        while (rs.next()) {
            String id = rs.getString("p_id");
            String name = rs.getString("p_name");
            int unitPrice = rs.getInt("p_unit_price");
            String description = rs.getString("p_description");
            String manufacturer = rs.getString("p_manufacturer");
            String category = rs.getString("p_category");
            int unitsInStock = rs.getInt("p_units_in_stock");
            String condition = rs.getString("p_condition");
            String filename = rs.getString("p_filename");
            int quantity = rs.getInt("p_quantity");

            Product product = new Product(id, name, unitPrice, description, manufacturer, category,
                    unitsInStock, condition, filename, quantity);
            productArrayList.add(product);
        }
        return productArrayList;
    }
    public static void close(Connection con, PreparedStatement ps, ResultSet rs) throws RuntimeException {
        try {
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
