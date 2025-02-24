<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	논리 연산자
		&&, ||
		
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = function() {
	let i = (6 > 7) && (6 === 7);
	console.log("i = " + i);
	let j = (6 < 7) || (6 === 7);
	console.log("j = " + j);
	
	let g = (6 % 2 === 0) ? "짝수" : "홀수";
}
</script>
</head>
<body>

</body>
</html>