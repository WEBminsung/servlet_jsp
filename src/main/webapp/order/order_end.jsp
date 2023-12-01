<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: 김민성
  Date: 2023-11-09
  Time: 오후 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    PrintWriter printWriter = response.getWriter();
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<%
    String shipping_cartId = "";
    String shipping_name = "";
    String shipping_shippingDate = "";
    String shipping_country = "";
    String shipping_zipCode = "";
    String shipping_addressName = "";

    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
        for (int i = 0; i < cookies.length; i++) {
            Cookie thisCookie = cookies[i];
            String n = thisCookie.getName();
            if (n.equals("Shipping_cartId"))
                shipping_cartId = URLDecoder.decode((thisCookie.getValue()), "utf-8");
            if (n.equals("Shipping_shippingDate"))
                shipping_shippingDate = URLDecoder.decode((thisCookie.getValue()), "utf-8");
        }
    }
%>
<jsp:include page="../top_menu.jsp" />
<div class="jumbotron">
    <div class="container">
        <h1 class="display-3">주문 완료</h1>
    </div>
</div>
<div class="container">
    <h2 class="alert alert-danger">주문해주셔서 감사합니다.</h2>
    <p>주문은 <%=shipping_shippingDate %>에 배송될 예정입니다! !
    <p> 주문번호 : <%= shipping_cartId %>
</div>
<div class="container">
    <p><a href="../index.jsp" class="btn btn-secondary"> &laquo; 상품 목록</a>
</div>
</body>
</html>
<%
    session.invalidate();

    for (int i = 0; i < cookies.length; i++) {
        Cookie thisCookie = cookies[i];
        String n = thisCookie.getName();
        if (n.equals("Shipping_cartId"))
            thisCookie.setMaxAge(0);
        if (n.equals("Shipping_name"))
            thisCookie.setMaxAge(0);
        if (n.equals("Shipping_shippingDate"))
            thisCookie.setMaxAge(0);
        if (n.equals("Shipping_country"))
            thisCookie.setMaxAge(0);
        if (n.equals("Shipping_zipCode"))
            thisCookie.setMaxAge(0);
        if (n.equals("Shipping_addressName"))
            thisCookie.setMaxAge(0);

        response.addCookie(thisCookie);
    }
%>