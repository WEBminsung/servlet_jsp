




<%@ page import="com.example.demo.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.demo.ProductRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<%
    ProductRepository productDAO = ProductRepository.getInstance();
%>
<%! String greeting = "현재 페이지는 VGA 그래픽 카드 상품 목록입니다.";
    String tagline = "하단 페이지 : 확인";%>

<div class="container">
    <div class="jumbotron" >
        <div class="container">
            <div style="display: flex">
                <div style="margin-right: 20px">
                    <img src="image/main_logo.png" style="border-radius: 100px; margin-top:60px " >
                </div>
                <div>
                    <img src="image/main_logo2.png" style="border-radius: 100px; margin-top: 10px">

                </div>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-md-3">
            <!-- 첫 번째 상품 -->
            <div class="card">
                <img src="image/category/mac.png" class="card-img-top" alt="상품 이미지">
                <div class="card-body">

                </div>
            </div>
        </div>

        <div class="col-md-3">
            <!-- 두 번째 상품 -->
            <div class="card">
                <img src="image/category/iphone.png" class="card-img-top" alt="상품 이미지">
                <div class="card-body">

                </div>
            </div>
        </div>

        <div class="col-md-3">
            <!-- 두 번째 상품 -->
            <div class="card">
                <img src="image/category/ipad.png" class="card-img-top" alt="상품 이미지">
                <div class="card-body">

                </div>
            </div>
        </div>

        <div class="col-md-3">
            <!-- 두 번째 상품 -->
            <div class="card">
                <img src="image/category/apple_watch.png" class="card-img-top" alt="상품 이미지">
                <div class="card-body">

                </div>
            </div>
        </div>

        <!-- 추가 상품들을 필요한 만큼 복사하여 붙여넣기 -->

    </div>








        <%
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
                    <a href="detail.jsp?id=<%=product.getId()%>">
                        <img src="image/product/<%=product.getFilename()%>" class="card-img-top" alt="...">
                    </a>

                    <div class="product-details">
                        <h3 class="product-title"><%=product.getName()%></h3>
                        <p><%=product.getDescription()%></p>
                        <p class="product-price"><%=product.getUnitPrice()%>원</p>
                    </div>
                </div>
            </div>

            <%
                }
            %>
        </div>
    </div>














    <div class="card bg-white text-white" >
        <img src="image/main_logo3.png" class="card-img" alt="..." style="width: auto; border-radius: 20px">

    </div>


    <div class="container">
        <div class="text-center">
            <img src="image/footer_logo.png" style="border-radius: 20px">
        </div>
        <hr>
    </div>



