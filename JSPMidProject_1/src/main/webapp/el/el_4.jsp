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
	
	List<String> sex = new ArrayList<String>();
	sex.add("sss");
	sex.add("sss");
	sex.add("sss");
	sex.add("sss");
	sex.add("sss");
	
	request.setAttribute("names", names);
	request.setAttribute("sex", sex);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>일반 for 문</h3>
		<ul>
			<%
				for (int i = 0; i < names.size(); i++) {
					String name = names.get(i);
					String sexx =  sex.get(i);
			%>
				<li><%= name %>(<%= sexx %>)</li>
			<%
				}
			%>
		</ul>
		<h3>&lt;c:forEach&gt;</h3>
			<ul>
				<c:forEach var="name" items="${names }" varStatus="s">
					<li>${name }(${sex[s.index ]})</li>
				</c:forEach>
			</ul>
</body>
</html>