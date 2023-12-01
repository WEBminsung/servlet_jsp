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
import java.nio.file.Paths;

@WebServlet("/product_add_process")
@MultipartConfig()
public class ProductAddProcess extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
        ProductRepository dao = ProductRepository.getInstance();
        dao.productAdd(product);

        resp.sendRedirect("admin/index_ad.jsp");
    }
}
