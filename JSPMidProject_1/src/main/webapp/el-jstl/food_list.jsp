<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<jsp:useBean id="model" class="com.sist.model.GoodsModel"/>    
<%
	model.goodsListData(request);
%>
<%--
Config.xml 작성 -> 드라이버, DB 설정
goods_mapper.xml 작성 -> SQL 문장
GoodsDAO 작성
GoodsModel 작성
JSP 작성
 --%>
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
	width: 960px;
}
p{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<c:forEach var="vo" items="${list }">
				<div class="col-md-3">
	   				<div class="thumbnail">
	      				<a href="#">
	        				<img src="${vo.goods_poster}" style="width: 230px; height: 200px">
        					<div class="caption">
          					<p>${vo.goods_name }</p>
          					<%-- 
              				<%= request.getAttribute("vo").getGoodsName() %>
              					vo.getGoodsName()
           					--%>
        					</div>
	      				</a>
	    			</div>
	  			</div>
			</c:forEach>
		</div>
		<div class="row" style="margin-top: 20px; margin-bottom: 50px;">
			<div class="text-center">
				<a href="#" class="btn btn-sm btn-info">이전</a>
					${curPage } page / ${totalPage } pages
				<a href="#" class="btn btn-sm btn-info">다음</a>
			</div>
		</div>
	</div>
</body>
</html>