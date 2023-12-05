package com.example.demo.mvc.controller;

import com.example.demo.mvc.model.Review_dao;
import com.example.demo.mvc.model.Review_dto;
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
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("*.review_do")
@MultipartConfig()
public class Review_controller extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String command = requestURI.substring(contextPath.length());

        if (command.equals("/ReviewListAction.review_do")) {
            requestReviewList(req);
            String prev = req.getParameter("prev");
            RequestDispatcher rd;
            if (prev.equals("admin")){
                rd = req.getRequestDispatcher("/admin/detail_ad.jsp");
            }else{
                rd = req.getRequestDispatcher("/detail.jsp");
            }
            rd.forward(req, resp);
        } else if (command.equals("/ReviewWriteAction.review_do")) {
            requestReviewWrite(req);

            resp.sendRedirect("/ReviewListAction.review_do?productId=" + req.getParameter("productId") + "&prev=" + req.getParameter("prev"));
        } else if (command.equals("/ReviewDeleteAction.review_do")) {
            requestReviewDelete(req);
            resp.sendRedirect("/ReviewListAction.review_do?productId=" + req.getParameter("productId") + "&prev=" + req.getParameter("prev"));
        }
    }
    public void requestReviewList(HttpServletRequest req) {
        String id = req.getParameter("productId");
        Review_dao dao = Review_dao.getInstance();
        ArrayList<Review_dto> reviews = dao.getReviewList(id);
        req.setAttribute("reviews", reviews);
    }
    public void requestReviewWrite(HttpServletRequest req) throws IOException, ServletException {



        String uploadPath = "C:/Users/ko758/Downloads/demo/src/main/webapp/image/review";
        Files.createDirectories(Paths.get(uploadPath));


        // 파일 파트 획득
        Part part = req.getPart("image");

        // 파일명 얻기
        String fileName = part.getSubmittedFileName();
        String filePath = uploadPath + "/" + fileName;
        if (fileName.isEmpty()){
            // 이미지가 없으면 아무것도 안함
        }else {
            Files.deleteIfExists(Paths.get(filePath));
            // 파일 저장
            try (InputStream input = part.getInputStream()) {
                Files.copy(input, Paths.get(filePath));
                System.out.println("이미지 업로드 성공");
            }
        }


        int num = Integer.parseInt(req.getParameter("num"));
        String id = req.getParameter("id");
        String content = req.getParameter("content");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
        String regist_day = formatter.format(new Date());
        int score = Integer.parseInt(req.getParameter("score"));
        String productId = req.getParameter("productId");

        Review_dto review = new Review_dto();
        review.setNum(num);
        review.setId(id);
        review.setContent(content);
        review.setRegist_day(regist_day);
        review.setScore(score);
        review.setFilename(fileName);
        review.setProductId(productId);

        Review_dao dao = Review_dao.getInstance();
        dao.addReview(review);
    }

    public void requestReviewDelete(HttpServletRequest req) {
        int num = Integer.parseInt(req.getParameter("num"));
        String productId = req.getParameter("productId");
        Review_dao dao = Review_dao.getInstance();
        dao.deleteById(num, productId);
    }
}
