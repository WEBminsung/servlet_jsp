package com.example.demo.mvc.controller;

import com.example.demo.mvc.model.Product;
import com.example.demo.mvc.model.Product_dao;
import jakarta.servlet.RequestDispatcher;
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

@WebServlet("*.product_do")
@MultipartConfig()
public class Product_controller extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String command = requestURI.substring(contextPath.length());

        if (command.equals("/ProductAddAction.product_do")) {
            requestProductAdd(req);
            RequestDispatcher rd = req.getRequestDispatcher("/admin/index_ad.jsp");
            rd.forward(req, resp);
        } else if (command.equals("/ProductUpdateAction.product_do")){
            requestProductUpdate(req);
            resp.sendRedirect("/admin/product_edit.jsp?edit=" + "update");

        } else if (command.equals("/ProductDeleteAction.product_do")) {
            requestProductDelete(req);
            resp.sendRedirect("/admin/product_edit.jsp?edit=" + "delete");
        }
    }

    public void requestProductAdd(HttpServletRequest req) throws IOException, ServletException {
        // 파일 업로드를 위한 설정
        String uploadPath = "C:/Users/ko758/Downloads/demo/src/main/webapp/image/product";
        Files.createDirectories(Paths.get(uploadPath));


        // 파일 파트 획득
        Part part = req.getPart("productImage");

        // 파일명 얻기
        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        String extension = fileName.split("\\.")[1];
        String productId = req.getParameter("productId");
        String filePath = uploadPath + "/" + productId + "." + extension;
        if (fileName.isEmpty()){
            // 이미지가 없으면 아무것도 안함
        }else {
            // 파일 저장
            try (InputStream input = part.getInputStream()) {
                Files.copy(input, Paths.get(filePath));
                System.out.println("이미지 업로드 성공");
            }
        }


        String name = req.getParameter("name");
        int unitPrice = Integer.parseInt(req.getParameter("unitPrice"));
        String description = req.getParameter("description");
        String manufacturer = req.getParameter("manufacturer");
        String category = req.getParameter("category");
        int unitsInStock = Integer.parseInt(req.getParameter("unitsInStock"));
        String condition = req.getParameter("condition");

        Product product = new Product();
        product.setId(productId);
        product.setName(name);
        product.setUnitPrice(unitPrice);
        product.setDescription(description);
        product.setManufacturer(manufacturer);
        product.setCategory(category);
        product.setUnitsInStock(unitsInStock);
        product.setCondition(condition);
        product.setFilename(productId + "." + extension);
        Product_dao dao = Product_dao.getInstance();
        dao.productAdd(product);
    }

    public void requestProductUpdate(HttpServletRequest req) throws IOException, ServletException {
        String uploadPath = "C:/Users/ko758/Downloads/demo/src/main/webapp/image/product";
        Files.createDirectories(Paths.get(uploadPath));


        // 파일 파트 획득
        Part part = req.getPart("productImage");

        // 파일명 얻기
        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        String extension = fileName.split("\\.")[1];
        String productId = req.getParameter("productId");
        String filePath = uploadPath + "/" + productId + "." + extension;
        if (fileName.isEmpty()){
            // 이미지가 없으면 아무것도 안함
        }else {
            // 기존 이미지 먼저 삭제
            Files.deleteIfExists(Paths.get(filePath));
            // 파일 저장
            try (InputStream input = part.getInputStream()) {
                Files.copy(input, Paths.get(filePath));

            }
        }


        String name = req.getParameter("name");
        int unitPrice = Integer.parseInt(req.getParameter("unitPrice"));
        String description = req.getParameter("description");
        String manufacturer = req.getParameter("manufacturer");
        String category = req.getParameter("category");
        int unitsInStock = Integer.parseInt(req.getParameter("unitsInStock"));
        String condition = req.getParameter("condition");

        Product product = new Product();
        product.setId(productId);
        product.setName(name);
        product.setUnitPrice(unitPrice);
        product.setDescription(description);
        product.setManufacturer(manufacturer);
        product.setCategory(category);
        product.setUnitsInStock(unitsInStock);
        product.setCondition(condition);
        product.setFilename(productId + "." + extension);
        Product_dao dao = Product_dao.getInstance();
        dao.productUpdate(product);

        req.setAttribute("productId", productId);

    }
    public void requestProductDelete(HttpServletRequest req) throws IOException {
        String uploadPath = "C:/Users/ko758/Downloads/demo/src/main/webapp/image/product";
        String filename = req.getParameter("filename");
        String realFileName = uploadPath + "/" + filename;
        Path filePath = Paths.get(realFileName);
        Files.deleteIfExists(filePath);


        Product_dao dao = Product_dao.getInstance();
        String productId = req.getParameter("id");
        dao.productDelete(productId);

    }
}
