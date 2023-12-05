<%--
  Created by IntelliJ IDEA.
  User: ko758
  Date: 2023-12-04
  Time: 오후 1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>상품 리뷰 페이지</title>
    <!-- 부트스트랩 CSS CDN 추가 -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">상품 리뷰</h2>
    <form action="/ReviewWriteAction.review_do" method="post" enctype="multipart/form-data">
    <!-- 상품 정보 -->
    <div class="card mb-4">
        <div class="card-body">
            <h5 class="card-title">작성자</h5>
            <input  class="card-text" value="<%=session.getAttribute("username")%>" name="id" >
        </div>
    </div>

    <!-- 리뷰 작성 폼 -->

    <div class="card">
        <div class="card-body">
            <h5 class="card-title">리뷰 작성</h5>

            <!-- 리뷰 평점 입력 -->
            <div class="form-group">
                <label for="rating">평점</label>
                <select class="form-control" id="rating" name="score">
                    <option value="5">5점 (매우 좋음)</option>
                    <option value="4">4점 (좋음)</option>
                    <option value="3">3점 (보통)</option>
                    <option value="2">2점 (나쁨)</option>
                    <option value="1">1점 (매우 나쁨)</option>
                </select>
            </div>

            <!-- 리뷰 내용 입력 -->
            <div class="form-group">
                <label for="reviewText">리뷰 내용</label>
                <textarea class="form-control" id="reviewText" name="content" rows="3"></textarea>
            </div>

            <div class="form-group">
                <label for="image">이미지 첨부</label>
                <input type="file" id="image" name="image">
            </div>
            <!-- 리뷰 제출 버튼 -->
            <button type="submit" class="btn btn-primary">리뷰 제출</button>
        </div>
    </div>
        <input name="productId" value="<%=request.getParameter("productId")%>" hidden="hidden">
        <input name="num" value="<%=request.getParameter("num")%>" hidden="hidden">
        <input name="prev" value="<%=request.getParameter("prev")%>" hidden="hidden">
    </form>
</div>

<!-- 부트스트랩 JS 및 Popper.js, jQuery CDN 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
