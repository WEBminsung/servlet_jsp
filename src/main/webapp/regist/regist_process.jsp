<%@ page import="com.example.demo.mvc.database.Db_connection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String id = request.getParameter("id");
    String password = request.getParameter("password");
    String name = request.getParameter("name");
    String gender = request.getParameter("gender");
    String birth = request.getParameter("birth");
    String mail = request.getParameter("mail");
    String phone = request.getParameter("phone");
    String address = request.getParameter("address");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
    String regist_day = formatter.format(new Date());
    try {
        Connection con = Db_connection.getconnection();
        String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, password);
        ps.setString(3, name);
        ps.setString(4, gender);
        ps.setString(5, birth);
        ps.setString(6, mail);
        ps.setString(7, phone);
        ps.setString(8, address);
        ps.setString(9, regist_day);
        int result = ps.executeUpdate();
        if (result == 0 ){
            throw new SQLException();
        }else{
            System.out.println("회원가입 완료 회원이름 = " + name);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    response.sendRedirect("/index.jsp");

%>