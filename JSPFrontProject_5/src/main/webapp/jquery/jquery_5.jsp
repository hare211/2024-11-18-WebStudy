<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() { // $(document).ready(function(){})
		/*
			$()
			 -- selector 태그 선택자
			 -- 브라우저 객체
			 	window, document, location
		 	$(this)
		*/
		//$('img').css('width', '200px');
		//$('img').css('height', '250px');
		$('img').css({
			'width':'250px',
			'height':'300px'
		});
		
		$('img').hover(function() { // if 문
			$(this).css('cursor', 'pointer'); // hover 된 것이 this
			// this 여러 같은 태그에 적용 시킬 때 this
			$(this).css('opacity', 0.2);
		}, function() { // else 문
			$(this).css('opacity', 1.0);
		});
	});
</script>
</head>
<body>
	<img src="m1.jpg">
	<img src="m2.jpg">
</body>
</html>