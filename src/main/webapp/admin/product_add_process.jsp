<%@ page import="java.nio.file.Files" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.nio.file.Path" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="java.nio.file.StandardCopyOption" %><%--
  Created by IntelliJ IDEA.
  User: 김민성
  Date: 2023-11-05
  Time: 오후 5:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 파일 업로드를 위한 설정
    DiskFileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);

    try {
        // 폼의 필드들을 파싱하여 리스트로 받음
        List<FileItem> items = upload.parseRequest(request);

        // 각 필드를 순회하면서 처리
        for (FileItem item : items) {
            // 파일 필드 확인
            if (!item.isFormField()) {
                // 파일을 저장할 경로 지정 (원하는 경로로 변경)
                String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                // 파일명 얻기
                String fileName = new File(item.getName()).getName();
                String filePath = uploadPath + File.separator + fileName;

                // 파일 저장
                item.write(new File(filePath));
                response.getWriter().write("파일 업로드 성공: " + fileName);
            }
        }
    } catch (Exception e) {
        response.getWriter().write("파일 업로드 실패");
    }
%>

<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
