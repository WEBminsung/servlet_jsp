package com.example.demo.mvc.controller;

import com.example.demo.mvc.model.Board_dao;
import com.example.demo.mvc.model.Board_dto;
import com.sun.jdi.IntegerType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

public class Board_controller extends HttpServlet {
    static final int limit = 5;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String command = requestURI.substring(contextPath.length());

        if(command.equals("/BoardListAction.do")){
            requestBoardList(req);
            RequestDispatcher rd = req.getRequestDispatcher("./board/board_list.jsp");
            rd.forward(req, resp);
        } else if (command.equals("/BoardWriteForm.do")) {
            requestLoginName(req);
            RequestDispatcher rd  = req.getRequestDispatcher("./board/board_writeform.jsp");
            rd.forward(req, resp);
        }
    }
    public void requestBoardList(HttpServletRequest req) {


        Board_dao dao = Board_dao.getInstance();
        String items = null;
        if(req.getParameter("items") != null){
            items = req.getParameter("items");
        }
        String text = null;
        if(req.getParameter("text") != null){
            text = req.getParameter("text");
        }

        int page_num = 1;
        if(req.getParameter("pageNum") != null){
            page_num = Integer.parseInt( req.getParameter("pageNum"));
        }
        int limit = Board_controller.limit;
        ArrayList<Board_dto> list = dao.getBoardList(page_num, limit, items, text);

        int total_record = dao.getListCount(items, text);
        int total_page = total_record / limit;

        req.setAttribute("total_record", total_record);
        req.setAttribute("total_page" , total_page);
        req.setAttribute("page_num", page_num);
        req.setAttribute("board_list", list);
    }

    public void requestLoginName(HttpServletRequest req){
        String id = req.getParameter("id");
        Board_dao dao = Board_dao.getInstance();
        String name = dao.getLoginNameById(id);
        req.setAttribute("name", name);
    }
}
