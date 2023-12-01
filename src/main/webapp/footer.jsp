<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: 김민성
  Date: 2023-10-16
  Time: 오후 6:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Date day = new java.util.Date();
    String am_pm;
    int hour = day.getHours();
    int minute = day.getMinutes();
    int second = day.getSeconds();
    if (hour / 12 == 0) {
        am_pm = "AM";
    } else {
        am_pm = "PM";
        hour = hour - 12;
    }
    String CT = hour + ":" + minute + ":" + second + " " + am_pm;

    String time = "Current connection time [ " + CT + "]\n";
%>
<footer class="container">
    <p>&copy; WebMarket</p>
    <%=time%>
</footer>
