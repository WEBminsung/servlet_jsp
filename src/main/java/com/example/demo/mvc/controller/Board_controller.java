package com.example.demo.mvc.controller;

import com.example.demo.mvc.model.Board_dao;
import com.example.demo.mvc.model.Board_dto;
import com.sun.jdi.IntegerType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("*.board_do")
public class Board_controller extends HttpServlet {
    static final int limit = 5;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String command = requestURI.substring(contextPath.length());

        if(command.equals("/BoardListAction.board_do")){
            requestBoardList(req);
            RequestDispatcher rd = req.getRequestDispatcher("./board/board_list.jsp");
            rd.forward(req, resp);
        } else if (command.equals("/BoardWriteForm.board_do")) {
            requestLoginName(req);
            RequestDispatcher rd  = req.getRequestDispatcher("./board/board_writeform.jsp");
            rd.forward(req, resp);
        } else if (command.equals("/BoardWriteAction.board_do")) {
            requestBoardWrite(req);
            RequestDispatcher rd = req.getRequestDispatcher("/BoardListAction.board_do");
            rd.forward(req, resp);
        } else if (command.equals("/BoardViewAction.board_do")) {
            requestBoardView(req);
            RequestDispatcher rd = req.getRequestDispatcher("./board/board_view.jsp");
            rd.forward(req, resp);
        } else if (command.equals("/BoardUpdateAction.board_do")) {
            requestBoardUpdate(req);
            RequestDispatcher rd = req.getRequestDispatcher("/BoardListAction.board_do");
            rd.forward(req, resp);
        } else if (command.equals("/BoardDeleteAction.board_do")) {
            requestBoardDelete(req);
            RequestDispatcher rd = req.getRequestDispatcher("/BoardListAction.board_do");
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

        if (total_record % limit != 0){
            total_page += 1;
        }

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
    public void requestBoardWrite(HttpServletRequest req) {
        Board_dao dao = Board_dao.getInstance();
        Board_dto board = new Board_dto();

        board.setId(req.getParameter("id"));
        board.setName(req.getParameter("name"));
        board.setSubject(req.getParameter("subject"));
        board.setContent(req.getParameter("content"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
        String regist_day = formatter.format(new Date());

        board.setHit(0);
        board.setRegist_day(regist_day);
        board.setIp(req.getRemoteAddr());

        dao.insertBoard(board);
    }

    public void requestBoardView(HttpServletRequest req) {
        int num = Integer.parseInt(req.getParameter("num"));
        Board_dao dao = Board_dao.getInstance();
        Board_dto board = dao.getBoardByNum(num);
        req.setAttribute("board", board);

    }

    public void requestBoardUpdate(HttpServletRequest req) {
        String name = req.getParameter("name");
        String subject =req.getParameter("subject");
        String content = req.getParameter("content");
        int num = Integer.parseInt(req.getParameter("num"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
        String regist_day = formatter.format(new Date());
        Board_dto board = new Board_dto();
        board.setName(name);
        board.setContent(content);
        board.setSubject(subject);
        board.setNum(num);
        board.setRegist_day(regist_day);

        Board_dao dao = Board_dao.getInstance();
        dao.updateBoard(board);
    }

    public void requestBoardDelete(HttpServletRequest req) {
        int num = Integer.parseInt(req.getParameter("num"));
        Board_dao dao = Board_dao.getInstance();
        dao.deleteBoard(num);
    }
}
