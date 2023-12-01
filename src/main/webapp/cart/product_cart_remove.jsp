<%@ page import="com.example.demo.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String productId = request.getParameter("id");
    ArrayList<Product> list = (ArrayList<Product>) session.getAttribute("cartlist");
    if (list == null) {
        list = new ArrayList<>();
    }

    Product removeProduct = null;
    for (Product product : list) {
        if (product.getId().equals(productId)) {
            removeProduct = product;
        }
    }
    
    list.remove(removeProduct);
    response.sendRedirect("product_cart.jsp");
%>