<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*" %>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"/>    <%-- useBean 자체가 싱글톤 --%>
<%
	String strPage = request.getParameter("page");
	if (strPage == null) {
		strPage = "1";
	}
	int curPage = Integer.parseInt(strPage);
	List<FoodVO> list = dao.foodListData(curPage);
	int totalPage = dao.getTotalPage();
	
	final int BLOCK = 10;
	int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
	int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
	
	if (endPage > totalPage) {
		endPage = totalPage;
	}
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
     				<a href="detail_before.jsp?fno=<%= vo.getFno() %>">
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
	</div>
</body>
</html>