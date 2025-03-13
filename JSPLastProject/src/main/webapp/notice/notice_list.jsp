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
 			<td>
 				<a href="../admin/notice_insert.do" class="btn btn-danger btn-sm">새글</a>
 			</td>
 		</tr>
 	</table>
 	<table class="table">
 		<tr>
 			<th width="10%" class="text-center">번호</th>
 			<th width="45%" class="text-center">제목</th>
 			<th width="15%" class="text-center">이름</th>
 			<th width="20%" class="text-center">작성일</th>
 			<th width="10%" class="text-center">조회수</th>
 		</tr>
 		<tr>
 			<td colspan="5">
 				<input type="checkbox" value="all">전체선택
 			</td>
 		</tr>
 		<c:forEach var="vo" items="${list }">
			<tr>
 			<td width="5%" class="text-center">${vo.no }
 				<input type="checkbox" name="dbox" value="${vo.no }">
 			</td>
 			<td width="5%" class="text-center">${vo.no }</td>
 			<td width="40%"><a href="../admin/notice_detail.do?no=${vo.no }">[#{vo.types}]&nbsp;${vo.subject }</a></td>
 			<td width="15%" class="text-center">${vo.name }</td>
 			<td width="20%" class="text-center">${vo.dbday }</td>
 			<td width="10%" class="text-center">${vo.hit }</td>
 		</tr>
 		</c:forEach>
 	</table>
 	<table class="table">
 		<tr>
 			<td class="text-left">
 				<input type="checkbox" name="fs" value="N">이름
 				<input type="checkbox" name="fs" value="S">제목
 				<input type="checkbox" name="fs" value="C">내용
 				<input type="text" name="ss" size="15" class="input-sm">
 				<input type="button" value="검색" class="btn-primary btn-sm">
 			</td>
 			<td class="text-right">
 				<a href="../admin/notice_list.do?page=${curPage > 1 ? curPage - 1 : curPage }" class="btn btn-outline-info btn-sm">이전</a>
 				${curPage } page / ${totalPage } pages
 				<a href="../admin/notice_list.do?page=${curPage < totalPage ? curPage + 1 : curPage }" class="btn btn-info btn-sm">다음</a>
 			</td>
 		</tr>
 	</table>
</body>
</html>