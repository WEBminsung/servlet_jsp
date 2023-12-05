<%@ page import="java.sql.Connection" %>
<%@ page import="com.example.demo.mvc.database.Db_connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("id");
    String password = request.getParameter("password");

    Connection con = Db_connection.getconnection();
    String sql = "select * from member where id = ? and password = ?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1, id);
    ps.setString(2, password);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        System.out.println("로그인 성공");
        String username = rs.getString("name");
        session.setAttribute("username", username);
        response.sendRedirect("/index.jsp");
    } else  {
        System.out.println("로그인실패");
    }
%>