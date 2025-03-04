<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table">
		<tr>
			<th class="text-center">사번</th>
			<th class="text-center">이름</th>
			<th class="text-center">직위</th>
			<th class="text-center">입사일</th>
			<th class="text-center">급여</th>
		</tr>
			<c:forEach var="vo" items="${list}"> 
				<tr>
					<td class="text-center">${vo.empno }</td>
					<td class="text-center">${vo.ename }</td>
					<td class="text-center">${vo.job }</td>
					<td class="text-center">${vo.dbday }</td>
					<td class="text-center">${vo.sal }</td>
				</tr>
			</c:forEach>
	</table>
</body>
</html>