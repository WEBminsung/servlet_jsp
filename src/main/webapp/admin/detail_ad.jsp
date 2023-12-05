<%@ page import="com.example.demo.mvc.model.Product_dao" %>
<%@ page import="com.example.demo.mvc.model.Product" %>
<%@ page import="com.example.demo.mvc.model.Review_dto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Product_dao productDAO = Product_dao.getInstance();
    String username = (String) session.getAttribute("username");
%>
<html>
<head>

    <title>Welcome</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script>
        function addToCart() {
            if (confirm("장바구니에 추가하시겠습니까?")) {
                document.addForm.submit();
            } else {
                document.addForm.reset();
            }
        }

        function login_confirm(num) {
            if (<%=username == null%>) {
                alert("로그인해주세요");
                window.location = "../login/user_login.jsp";
                return;
            }
                window.location = "/review/review_write.jsp?productId=" + "<%=request.getParameter("productId")%>" + "&num=" + num;
            }
        function confirm_delete(num, productId){
            if (confirm("삭제하시겠습니까?")) {
                window.location =  "ReviewDeleteAction.review_do?num=" + num + "&productId=" + productId + "&prev=admin";
            }

        }
    </script>
</head>
<body>
<%@ include file="top_banner_ad.jsp" %>
<%@ include file="top_menu_ad.jsp" %>
<div class="jumbotron">
    <div class="container">
        <h1 class="display-3">상품 상세 정보</h1>
    </div>
</div>
    <%
		String id = request.getParameter("productId");
		Product product = productDAO.findById(id);
	%>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h3><%=product.getName()%></h3>
            <p><%=product.getDescription()%>
            <p><b>상품 코드 : </b><span class="badge badge-danger"> <%=product.getId()%></span>
            <p><b>제조사</b> : <%=product.getManufacturer()%>
            <p><b>분류</b> : <%=product.getCategory()%>
            <p><b>재고 수</b> : <%=product.getUnitsInStock()%>
            <h4><%=product.getUnitPrice()%>원</h4>
            <div class="card bg-dark text-white">
                <img src="/image/product/<%=product.getFilename()%>" class="card-img" alt="...">
                <div class="card-img-overlay">
                    <h5 class="card-title">상품 이미지 원본</h5>
                    <p class="card-text">출처 : 구글 검색</p>
                </div>
            </div>
            <p><form action="../cart/product_cart_add.jsp?id=<%=product.getId()%>" name="addForm" method="post">
                <a href="#" class="btn btn-info" onclick="addToCart()"> 상품 주문 &raquo;</a>
                <a href="../cart/product_cart.jsp" class="btn btn-info"> 장바구니</a>
            </form>
        </div>
    </div>
    <hr>
</div>
<div class="container mt-5">
    <h2 class="mb-4">상품 리뷰 목록</h2>
    <%
        ArrayList<Review_dto> reviews = (ArrayList<Review_dto>) request.getAttribute("reviews");
    %>
    <button onclick="login_confirm(<%=reviews.size()%>)">리뷰 작성</button>

    <!-- 상품 리뷰 테이블 -->
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">작성자</th>
            <th scope="col">리뷰 내용</th>

            <th scope="col">작성일</th>
            <th scope="col">평점</th>
            <th scope="col">이미지</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Review_dto review: reviews) {
        %>

        <tr>
            <th scope="row"><%=review.getNum()%></th>
            <td><%=review.getId()%></td>
            <td><pre>
        <%=review.getContent()%>
            </pre></td>
            <td><%=review.getRegist_day()%></td>
            <td><%=review.getScore()%></td>
            <td><img src="/image/review/<%=review.getFilename()%>" alt="상품 A 이미지" style="max-width: 100px;"></td>

            <td>
                <%if(username != null && username.equals(review.getId())){%>
                    <button class="btn btn-primary" onclick="confirm_delete(<%=review.getNum()%>, '<%=review.getProductId()%>')">삭제</button>
<%--                        <button onclick="window.location = '/test/test.jsp?productId=' + '<%=review.getProductId()%>'">dsf</button>--%>
                <%}%>
            </td>
        </tr>

        <%}%>
        <!-- 추가적인 상품 리뷰 데이터는 여기에 추가 -->
        </tbody>
    </table>
</div>
<%@ include file="footer_ad.jsp" %>
</body>
</html>

