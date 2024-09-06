<%@page import="com.multicampus.biz.board.BoardDAOJdbc"%>
<%@page import="com.multicampus.biz.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. 사용자 입력정보 추출
	String seq = request.getParameter("seq");
	
	// 2. DB 연동 처리
	BoardVO vo = new BoardVO();
	vo.setSeq(Integer.parseInt(seq));
	
	BoardDAOJdbc dao = new BoardDAOJdbc();
	dao.deleteBoard(vo);
	
	// 3. 화면 이동
	response.sendRedirect("getBoardList.jsp");
%>