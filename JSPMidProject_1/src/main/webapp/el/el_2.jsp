<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 600px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="text-cnter">
				<ul class="pagination">
					<%
						for (int i = 0; i <= 10; i++) {
							
					%>
						<li><a href="#"><%= i %></a></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
					<c:forEach var="i" begin="1" end="10">
						<li><a href="#">${i }</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>	
</body>
</html>