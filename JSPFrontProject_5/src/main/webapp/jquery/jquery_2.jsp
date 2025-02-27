<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function() {
	/*
		$(선택자).css() => 태그의 CSS 적용
		$(선택자).attr() => 속성의 값을 변경
		--------------
			<img src="">
			$('img').attr('src') -> getter
			$('img').attr('src', '') -> setter
		$('selector').val() -> <input> / <select> / <textarea>
		$('selector').val('admin')
		
		$('selector').text() -> getter
		$('selector').text('') -> setter
		
		innerHTML
		$('selector').html()
		$('selector').html("<h1></h1>")
		
	*/
	// 자바스크립트
	/*
	let h1 = document.querySelectorAll('h1');
	for (let i = 0; i < h1.lenght; i++) {
		h1[i].style.backgroundColor = 'yellow';
		h1[i].style.color = 'cyan';
	}
	*/
	// JQuery
	$('h1').css("backgroundColor", "green");
	$('h1').css("color", "blue") ;
});
</script>
</head>
<body>
	<h1>Heelo jquery</h1>
	<h1>Heelo jquery2</h1>
	<h1>Heelo jquery3</h1>
</body>
</html>