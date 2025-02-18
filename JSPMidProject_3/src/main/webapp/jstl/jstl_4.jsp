<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>자바 문자열 처리</h3>
	<%
		String msg = "ㅇㅇㅇ";
	%>
	<%= msg.length()%><br>
	<%= msg.substring(0,2) %><br>
	<%= msg.replace('ㅇ', 'ㅁ') %>
	<h3>JSTL 문자열 처리</h3>
	<c:set var="msg" value="ㅇㅇㅇ"/>
	${fn:length(msg) }<br>
	${fn:substring(msg, 0, 1) }<br>
	${fn:replace(msg, 'ㅇ', 'ㅁ') }<br>
</body>
</html>