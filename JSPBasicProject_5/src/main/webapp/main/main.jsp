<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String jsp = "home.jsp";
%>    
<!DOCTYPE html>
<!--
Template Name: Gravity
Author: <a href="https://www.os-templates.com/">OS Templates</a>
Author URI: https://www.os-templates.com/
Licence: Free to use under our free template licence terms
Licence URI: https://www.os-templates.com/template-terms
-->
<html>
<head>
<title>Gravity</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="../layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
</head>
<body id="top">
<!-- HEADER -->
<%--
	pageContext.include("header.jsp"); // header.jsp 를 불러와서 붙인다
--%>
<%--<jsp:include page="header.jsp"></jsp:include> --%>
<%@ include file="header.jsp" %> <%-- 이 태그는 바꿀 수 없다 file="" < 이 부분을 변수 선언하여 바꿀 수 없음 --%> 
<!-- MAIN -->
<%--
	pageContext.include("home.jsp");
--%>
<%-- <jsp:include page="home.jsp"></jsp:include> String jsp = "home.jsp" 를 통해서 page=에 대입 가능 --%>
<%@ include file="home.jsp" %>
<!-- FOOTER -->
<%--
	pageContext.include("footer.jsp");
--%>
<%-- <jsp:include page="footer.jsp"></jsp:include>--%>
<%@ include file="footer.jsp" %>

<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a> 
<!-- JAVASCRIPTS --> 
<script src="../layout/scripts/jquery.min.js"></script> 
<script src="../layout/scripts/jquery.backtotop.js"></script> 
<script src="../layout/scripts/jquery.mobilemenu.js"></script> 
<script src="../layout/scripts/jquery.flexslider-min.js"></script>
</body>
</html>