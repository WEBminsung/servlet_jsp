<%@ page import="java.util.Date" %>
<%@ page import="java.io.PrintWriter" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page buffer="1kb" autoFlush="true"%>
<html>
<head>

    <title>Welcome</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <style>
        body {
            font-family: 'Lucida Handwriting', 'Comic Sans MS', cursive;
        }



    </style>
    <script>
        function confirm_delete(id, filename){
            if(confirm("delete?")){
                location.href = "/product_delete_process?id=" + id + "&filename=" + filename;
            }
        }
    </script>
</head>
<body>
<%@ include file="top_banner_ad.jsp"%>
<%@ include file="top_menu_ad.jsp" %>








<style>
    .card{
        align-items: center;
        border: white solid;
    }
    .card-img-top {
        width: 170px;
        border-radius: 100px;
    }




    .product-card {
        margin-bottom: 20px; /* 각 상품 카드 간격 조정 */
        border: 1px solid #dee2e6; /* 카드 테두리 추가 */
        border-radius: 8px; /* 카드 테두리 둥글게 조정 */
    }

    .product-card img {
        max-width: 100%; /* 이미지 가로폭 조정 */
        height: auto;
        border-radius: 8px 8px 0 0; /* 이미지 테두리 둥글게 조정 */
    }

    .product-details {
        padding: 15px;
        text-align: center;
    }

    .product-title {
        font-size: 18px;
        font-weight: bold;
        margin-top: 10px;
    }

    .product-price {
        font-size: 16px;
        color: #28a745; /* 가격 표시 색상 지정 */
    }
</style>


<div class="container">
    <div class="jumbotron" >
        <div class="container">
            <div style="display: flex">
                <div style="margin-right: 20px">
                    <img src="../image/main_logo.png" style="border-radius: 100px; margin-top:60px " >
                </div>
                <div>
                    <img src="../image/main_logo2.png" style="border-radius: 100px; margin-top: 10px">

                </div>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-md-3">
            <!-- 첫 번째 상품 -->
            <div class="card">
                <img src="../image/category/mac.png" class="card-img-top" alt="상품 이미지">
                <div class="card-body">

                </div>
            </div>
        </div>

        <div class="col-md-3">
            <!-- 두 번째 상품 -->
            <div class="card">
                <img src="../image/category/iphone.png" class="card-img-top" alt="상품 이미지">
                <div class="card-body">

                </div>
            </div>
        </div>

        <div class="col-md-3">
            <!-- 두 번째 상품 -->
            <div class="card">
                <img src="../image/category/ipad.png" class="card-img-top" alt="상품 이미지">
                <div class="card-body">

                </div>
            </div>
        </div>

        <div class="col-md-3">
            <!-- 두 번째 상품 -->
            <div class="card">
                <img src="../image/category/apple_watch.png" class="card-img-top" alt="상품 이미지">
                <div class="card-body">

                </div>
            </div>
        </div>

        <!-- 추가 상품들을 필요한 만큼 복사하여 붙여넣기 -->

    </div>






    <%@ page import="com.example.demo.Product" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.example.demo.ProductRepository" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>


        <%

            ProductRepository productDAO = ProductRepository.getInstance();

	        ArrayList<Product> listOfProducts = productDAO.findAll();
        %>
    <div class="container">
        <div class="row">
            <%
                for (int i = 0; i < listOfProducts.size(); i++) {
                    Product product = listOfProducts.get(i);
            %>

            <div class="col-md-4">
                <div class="card product-card">
                    <a href="detail_ad.jsp?id=<%=product.getId()%>">
                        <img src="../image/product/<%=product.getFilename()%>" class="card-img-top" alt="...">
                    </a>

                    <div class="product-details">
                        <h3 class="product-title"><%=product.getName()%></h3>
                        <p><%=product.getDescription()%></p>
                        <p class="product-price"><%=product.getUnitPrice()%>원</p>
                        <%
                            String edit = request.getParameter("edit");
                            if (edit.equals("update")){
                        %>
                        <a href="product_update.jsp?id=<%=product.getId()%>" type="button" class="btn btn-warning" >수정</a>
                        <%}else{%>
                        <a onclick="confirm_delete('<%=product.getId()%>', '<%=product.getFilename()%>')" type="button" class="btn btn-warning" >삭제</a>
                        <%}%>
                    </div>

                </div>

            </div>

            <%
                }
            %>
        </div>
    </div>














    <div class="card bg-white text-white" >
        <img src="../image/main_logo3.png" class="card-img" alt="..." style="width: auto; border-radius: 20px">

    </div>


    <div class="container">
        <div class="text-center">
            <img src="../image/footer_logo.png" style="border-radius: 20px">
        </div>
        <hr>
    </div>




<%@ include file="footer_ad.jsp" %>
BufferSize: <%=out.getBufferSize()%><br>
Remaining: <%=out.getRemaining()%>

</body>
</html>