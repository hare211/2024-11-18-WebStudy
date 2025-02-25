<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	// 선언적 함수 선언
	function plus1(a, b) {
		return a + b;
	};
	// 익명 함수
	let plus2 = function(a, b) {
		return a + b;
	};
	// 사용빈도 많음
	let plus3 = (a, b) => {
		return a + b;
	};
	let plus4 = (a, b) => a + b;
	
	window.onload = () => {
		let a = 10;
		let b = 20;
		
		let p1 = plus1(a, b);
		let p2 = plus2(a, b);
		let p3 = plus3(a, b);
		let p4 = plus4(a, b);
		
		// 브라우저 출력
		document.write("p1 = " + p1 + "<br>");
		document.write("p2 = " + p2 + "<br>");
		document.write("p3 = " + p3 + "<br>");
		document.write("p4 = " + p4 + "<br>");
	};
</script>
</head>
<body>

</body>
</html>