<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.demo.mvc.model.Product" %>
<%@ page import="com.example.demo.mvc.model.Product_dao" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    Product_dao dao = Product_dao.getInstance();

    String id = request.getParameter("id");
    Product product = dao.findById(id);
    product.setQuantity(1);

    ArrayList<Product> list = (ArrayList<Product>) session.getAttribute("cartlist");
    if (list == null) {
        list = new ArrayList<>();
        list.add(product);
        session.setAttribute("cartlist", list);
    } else {
        int cnt = 0;
        for (Product cartItem : list) {
            if (cartItem.getId().equals(id)) {
                cnt += 1;
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            }
        }
        if (cnt == 0) {
            list.add(product);
            session.setAttribute("cartlist", list);
        }
    }

    response.sendRedirect("/detail.jsp?id=" + id);
%>
