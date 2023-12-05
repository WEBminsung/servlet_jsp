<%--
  Created by IntelliJ IDEA.
  User: ko758
  Date: 2023-12-04
  Time: 오후 6:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.removeAttribute("username");
    System.out.println(session.getAttribute("username"));
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button onclick="f(1, 2)"></button>
<script>
    function f() {

    }
</script>
</body>
</html>
