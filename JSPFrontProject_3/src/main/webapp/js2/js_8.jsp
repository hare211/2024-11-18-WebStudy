<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	onload = () => {
		let div = document.querySelectorAll("#box");
		for (let i = 0; i < div.length; i++) {
			div[i].addEventListener('mousedown', function(e) {
				div[i].style.backgroundColor = "red";
			});
			div[i].addEventListener('mouseup', function(e) {
				e.currentTarget.style.background = "orange";
			});
		}
		
	};
</script>
<style type="text/css">
#box{
	width: 200px;
	height: 200px;
	border: 1px solid black;
}
</style>
</head>
<body>
	<div id="box"></div>
	<div id="box"></div>
	<div id="box"></div>
	<div id="box"></div>
	<div id="box"></div>
	<div id="box"></div>
	<div id="box"></div>
</body>
</html>