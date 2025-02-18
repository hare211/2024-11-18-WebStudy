<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	BootStrap
	--------- 라이브러리
	1. 출력 범위 설정
		container : 좌우 960px 여부
		container-fluid : 풀화면
	2. 출력 범위 안 설정(container)
		row : 가로 분할
		
		전체적으로 세로로 12등분
		col-sm-4 col-sm-8 => 12 => lg / sm / md / xs
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	border: 1px solid black;
	height: 600px;
}
.row{
	background-color: cyan;
	border: 1px solid black;
	height: 300px;
}
.col-sm-8{
	background-color: green;
	border: 1px solid black;
	height: 300px;
}
.col-sm-4{
	background-color: magenta;
	border: 1px solid black;
	height: 300px;
}
</style>
</head>
<body>
	<div class="container">
	<div class="row"></div>
	<div class="row">
		<div class="col-sm-8"></div>
		<div class="col-sm-4"></div>
	</div>
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4"></div>
		<div class="col-sm-4"></div>
	</div>
	</div>
</body>
</html>