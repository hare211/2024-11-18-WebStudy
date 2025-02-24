<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<html>
<head>
<title>Gravity</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="../layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
</head>
<body id="top">
<jsp:include page="header.jsp"/>
<jsp:include page="${main_jsp }"/>
<jsp:include page="footer.jsp"/>

<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a> 
<%-- Jquery 가 충돌하면 화면 출력이 안됨 --%>
<!-- JAVASCRIPTS --> <!-- CSS, JavaScript 는 항상 메인에 위치해야 함 -->
<script src="../layout/scripts/jquery.min.js"></script> 
<script src="../layout/scripts/jquery.backtotop.js"></script> 
<script src="../layout/scripts/jquery.mobilemenu.js"></script> 
<script src="../layout/scripts/jquery.flexslider-min.js"></script>
</body>
</html>