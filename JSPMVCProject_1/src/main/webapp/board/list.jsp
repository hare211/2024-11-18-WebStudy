<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
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
	width: 700px;
}
</style>
</head>
<body>
	<div class="container">
		<h3 class="text-center">자유게시판</h3>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<a href="insert.do" class="btn btn-sm btn-success">새글</a>
					</td>
				</tr>
			</table>
			<!-- 데이터 출력 -->
			<table class="table">
				<tr>
					<th class="text-center" width=10%>번호</th>
					<th class="text-center" width=45%>제목</th>
					<th class="text-center" width=15%>이름</th>
					<th class="text-center" width=20%>작성일</th>
					<th class="text-center" width=10%>조회수</th>
				</tr>
				<c:forEach var="vo" items="${list }">
				<tr>
					<td class="text-center" width=10%>${vo.no }</td>
					<td width=45%><a href="detail.do?no=${vo.no }">${vo.subject }</a>
						<c:if test="${vo.dbday == today }">
							<sup><img src="image/new.png"></sup>
						</c:if>
					</td>
					<td class="text-center" width=15%>${vo.name }</td>
					<td class="text-center" width=20%><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"/></td>
					<td class="text-center" width=10%>${vo.hit }</td>
				</tr>
				</c:forEach>
			</table>
			<table class="table">
				<tr>
					<td class="text-center">
						<a href="list.do?page=${curPage > 1 ? curPage - 1 : curPage }" class="btn btn-sm btn-danger">이전</a>
						${curPage } page / ${totalPage } pages
						<a href="list.do?page=${curPage < totalPage ? curPage + 1 : curPage }" class="btn btn-sm btn-danger">다음</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>