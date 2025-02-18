<%@page import="com.sist.vo.FoodVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.model.*" %>
<jsp:useBean id="model" class="com.sist.model.FoodModel"/>    
<%
	model.foodListData(request);

	List<FoodVO> list = (List<FoodVO>)request.getAttribute("list");
	int curPage = (int)request.getAttribute("curPage");
	int totalPage = (int)request.getAttribute("totalPage");
	int startPage = (int)request.getAttribute("startPage");
	int endPage = (int)request.getAttribute("endPage");
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
	margin: 0px auto;
	width: 800px;
}
p{
	overflow: hidden;
	white-space: nowrap; /* 자동 줄바꿈 X */
	text-overflow: ellipsis; /* 넘어가는 건 ... 처리 */
}
</style>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h3 class="text-center">맛집 목록</h3>
		</div>
		<div class="row">
			<%
				for (FoodVO vo:list) {
			%>
			<div class="col-md-3">
   				<div class="thumbnail">
     				<a href="food_detail.jsp?fno=<%= vo.getFno() %>">
     					<img src="<%= vo.getPoster() %>" alt="Lights" style="width:100%">
       			 		<div class="caption">
          					<p><%= vo.getName() %></p>
     					</div>
     				</a>
 				</div>
  			</div>
			<%
				}
			%>
		</div>
			<div style="height: 20px;">
				<div class="row text-center">
					<ul class="pagination">
					<!-- 페이지 부분 -->
						<%	// 1 11 21 31...
							if (startPage > 1) {
								
						%>
						<li><a href="main.jsp?page=<%= startPage - 1 %>">&lt;</a></li>
						
						<%
							}
							for(int i = startPage; i<= endPage; i++) {
						%>
						<li <%= curPage == i ? "class=active" : "" %>>
						<a href="main.jsp?page=<%= i %>"><%= i %></a></li>
						<%
							}
							if (endPage < totalPage) {
						%>
						<li><a href="main.jsp?page=<%= endPage + 1 %>">&gt;</a></li>
						<%
							}
						%>
					<!-- 페이지 부분 끝 -->
					</ul>
				</div>
			</div>

	</div>
</body>
</html>