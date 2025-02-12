<%@page import="com.sist.dao.DataBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
	DataBoardDAO dao = DataBoardDAO.newInstance();
	dao.dataBoardDelete(Integer.parseInt(no));
	
	response.sendRedirect("list.jsp");
%>

