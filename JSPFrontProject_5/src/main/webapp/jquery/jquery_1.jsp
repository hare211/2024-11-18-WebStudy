<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	JQuery: 자바스크립트 라이브러리
	=> 태그, 속성을 제어하는 프로그램: 문서 객체
	   document.querySelector("")
	   $("selector").
	   <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	   
	   1. window.onload(){}
	   	=> $(function(){})
 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function() {
	let h1 = document.querySelector('#h1');
	h1.style.color = 'red';
	
	let h2 = document.querySelector('.h2');
	h2.style.color = 'blue';
	
	let h3 = document.querySelector('h2');
	h3.style.color = 'green';
	
	$('#h1').css("color", "red");
	$('.h2').css("color", "blue");
	$('h2').css("color", "green");
})
</script>
</head>
<body>
	<h1 id="h1">hello jquery</h1>
	<h1 class="h2">hello jquery</h1>
	<h2>hello jquery</h2>
</body>
</html>