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
		// 같은 태그 여러 개가 있는 경우 인덱스 번호를 이용 -> eq 는 0 번부터 시작
		//$('span:nth-child(1)').text('Hello JQuery');
		$('span:eq(0)').text('Hello JQuery'); // textContent
		$('span:eq(1)').text('<font color=red>Hello JQuery</font>');
		$('span:eq(1)').html('<font color=red>Hello JQuery</font>'); // eq(1).text() 를 덮어씌움 // innerHTML
		// appendChild() -> append()
	});
</script>
</head>
<body>
	<span>
		
	</span><br>
	<span>
	
	</span>
</body>
</html>