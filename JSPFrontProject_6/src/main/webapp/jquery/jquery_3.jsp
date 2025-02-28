<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 350px;
}
</style>
<script type="text/javascript">
	$(function() {
		$('img').css({
			width: 120,
			height: 180
		});
		$('img').mousedown(function() { // 마우스 눌렀을 때
			$(this).attr('class', 'img-circle'); // img-circle -> bootstrap
		});
		
		$('img').mouseup(function() {
			$(this).attr('class', ''); // attr -> attribute
		});
		
		$('img').hover(function() {
			$(this).css('cursor', 'pointer');
			$(this).css('opacity', 0.3);
		}, function() {
			$(this).css('cursor', 'none');
			$(this).css('opacity', 1.0);
		});
	});
</script>
</head>
<body>
	<img src="m1.jpg">
	<img src="m2.jpg">
	<img src="m3.jpg">
	<img src="m4.jpg">
	<img src="m5.jpg">
	<img src="m6.jpg">
	<img src="m7.jpg">
</body>
</html>