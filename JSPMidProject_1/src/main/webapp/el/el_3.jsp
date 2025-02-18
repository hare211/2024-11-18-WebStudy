<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%
	// 자바 파일에서 전송
	List<String> names = new ArrayList<String>();
	names.add("ddd");
	names.add("ddd");
	names.add("ddd");
	names.add("ddd");
	names.add("ddd");
	
	// ${} request / session 에 저장
	request.setAttribute("list", names);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>일반 자바</h3>
	<ul>
	<%
		int i = 0;
		for (String name:names) {
	%>
		<li><%= i + 1%>.<%= name %></li>
	<%
		i++;
		}
	%>
	</ul>
	<h3>&lt;c:forEach&gt; 구문</h3>
		<ul>
			<c:forEach var="name" items="${list }">
				<li>${name}</li>
			</c:forEach>
		</ul>
</body>
</html>