package com.example.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@WebServlet("/product_delete_process")
public class ProductDeleteProcess extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 이미지 삭

        String uploadPath = "C:/Users/ko758/Downloads/demo/src/main/webapp/image/product";
        String filename = req.getParameter("filename");
        String realFileName = uploadPath + "/" + filename;
        Path filePath = Paths.get(realFileName);
        Files.deleteIfExists(filePath);


        ProductRepository dao = ProductRepository.getInstance();
        String productId = req.getParameter("id");
        dao.productDelete(productId);

        resp.sendRedirect("admin/product_edit.jsp?edit=delete");
    }
}
