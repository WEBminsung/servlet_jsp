<%--
  Created by IntelliJ IDEA.
  User: ko758
  Date: 2023-12-04
  Time: 오후 5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>회원가입</title>
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h2 class="text-center">회원가입</h2>
        </div>
        <div class="card-body">
            <form action="regist_process.jsp" method="post">
                <div class="mb-3">
                    <label for="id" class="form-label">아이디:</label>
                    <input type="text" id="id" name="id" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">비밀번호:</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">이름:</label>
                    <input type="text" id="name" name="name" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="gender" class="form-label">성별:</label>
                    <input type="text" id="gender" name="gender" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="birth" class="form-label">생년월일:</label>
                    <input type="text" id="birth" name="birth" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="mail" class="form-label">이메일:</label>
                    <input type="email" id="mail" name="mail" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">전화번호:</label>
                    <input type="text" id="phone" name="phone" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">주소:</label>
                    <input type="text" id="address" name="address" class="form-control">
                </div>
                <div class="mb-3">
                    <input type="submit" value="가입하기" class="btn btn-primary">
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 부트스트랩 및 자바스크립트 CDN 추가 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
