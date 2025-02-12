<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//response.sendRedirect("c.jsp"); // id : null
	pageContext.forward("c.jsp"); // id : admin
	
	// 기존 request, response 객체 유지
	// 서버 내부에서 이동
	// 처리 후 이전 코드로 돌아오지 않음
	// URL 변경 없음
%>