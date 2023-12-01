<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("cartId");
    if (id == null || id.trim().equals("")) {
        response.sendRedirect("product_cart.jsp");
        return;
    }

    session.invalidate(); // 세션 초기화

    response.sendRedirect("product_cart.jsp");
%>
