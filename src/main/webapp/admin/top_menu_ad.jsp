<%--
  Created by IntelliJ IDEA.
  User: Kim Minseong
  Date: 2023-10-16
  Time: 6:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Add Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<nav class="navbar navbar-expand navbar-light bg-white">


    <div class="container">
        <div class="navbar-header">
            <img src="http://localhost:8080/image/logo2.png" class="img-fluid" alt="main_image" width="50px" style="margin-right: 30px;margin-left: 30px;margin-bottom: 10px">
            <a class="navbar-brand" href="index_ad.jsp">Menu: Home</a>
            <a class="navbar-brand" href="/login/user_login.jsp">Login</a>
            <a class="navbar-brand" href="index_ad.jsp">Sign up</a>
            <a class="navbar-brand" href="index_ad.jsp">Customer Service</a>
            <a class="navbar-brand" href="../index.jsp">Normal Mode</a>
            <a class="navbar-brand" href="product_add.jsp">P Regist</a>
            <a class="navbar-brand" href="product_edit.jsp?edit=update">Update</a>
            <a class="navbar-brand" href="product_edit.jsp?edit=delete">Delete</a>
            <a href="../cart/product_cart.jsp"><img src="http://localhost:8080/image/cart.png" style="border-radius: 20px"></a>
        </div>
    </div>
</nav>

<!-- Add Bootstrap JS and additional scripts -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
