<%@page import="com.sist.model.FoodModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%
	FoodModel model = new FoodModel();
	model.foodListData(request);
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
			<c:forEach var="vo" items="${list }">
			<div class="col-md-3">
   				<div class="thumbnail">
     				<a href="detail.jsp?fno=${vo.fno }">
     					<img src="http://www.menupan.com${vo.poster }" alt="Lights" style="width: 239px; height: 150px">
       			 		<div class="caption">
          					<p>${vo.name }</p>
     					</div>
     				</a>
 				</div>
  			</div>
  			</c:forEach>
		<%-- </div>
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
			<div class="row">
				<h3>최근 방문 맛집</h3>
				<a href="cookie_all_delete.jsp" class="btn btn-sm btn-danger">전체삭제</a>
				<hr>
						<%
						    List<FoodVO> fList = new ArrayList<>();
						    Cookie[] cookies = request.getCookies();
						    
						    if (cookies != null) {
						        for (int i = cookies.length - 1; i >= 0; i--) {
						            if (cookies[i].getName().startsWith("food_")) {
						                String fno = cookies[i].getValue();
						                FoodVO vo = dao.getFoodDetail(Integer.parseInt(fno), 0);
						                fList.add(vo);
						            }
						        }
						    }
						
						    int count = 0;
						    for (FoodVO vo : fList) {
						        if (count >= 5) {
						            break;
						        }
						%>
						    <div class="col-md-2">
						        <div class="thumbnail">
						            <a href="#">
						                <img src="<%= vo.getPoster() %>" alt="Food Image" style="width:75px; height:75px;">
						                <div class="caption">
						                    <p><a href="cookie_delete.jsp?fno=<%= vo.getFno() %>" class="btn btn-xs btn-success">삭제</a></p>
						                </div>
						            </a>
						        </div>
						    </div>
						<%
						        count++;
						    }
						%>
			</div>
				 --%>
	</div>
</body>
</html>