<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.vo.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%
	SawonVO vo = new SawonVO();
	vo.setSabun(1);
	vo.setName("홍길동");
	vo.setDept("개발부");
	vo.setJob("대리");
	vo.setPay(3500);
	// ${} => 출력 X
			
	request.setAttribute("vo", vo);			
%>   
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
	width: 560px;
	margin: 0px auto;
}
</style>
</head>
<body>
	<div class="container">
		<h3 class="text-center">구구단</h3>
			<table class="table">
				<tr>
					<%
						for(int i = 2; i <= 9; i++) {
							
					%>
					<th class="text-center"><%= i + "단" %></th>
					<%
						}
					%>
				</tr>
			</table>
	</div>
</body>
</html>