<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	EL : 표현식(브라우저에 데이터 출력)
	<%= %> 를 대체
	형식)
		${값}
		
		=> 연산자
		=> 내장 객체
		
		String name = "홍길동";
		${name} => 출력 X
		<%= name %> => 출력 O
		
		${값}
		---- request.getAttribute("name")
			 ${name}
			 session.setAttribute("id", "admin")
			 	=> ${id} => admin
			 	=> request / session 에 존재하는 값만 출력 가능
			 => ${name}
			 	  ----> key
			 	  
	 	  	 request.setAttribute("name", "홍길동");
	 	  	 session.setAttribute("name", "홍길동");
	 	  	 
	 	  	 ${name} => request
	 	  	 ${requestScope.name} => 생략 가능
	 	  	 ${sessionScope.name}
	 	  	 
	 	  	 우선순위 => request -> session -> application
 --%>    
 <%
 	String name = "홍길동";
 	request.setAttribute("name", "박문수");
 	session.setAttribute("name", "심청이");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름: ${name } <br>
	이름: ${sessionScope.name } <br>
	이름: <%= name %>
</body>
</html>