<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 변수 선언 / 출력 형식 / 주석 처리 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// 한줄 주석
/*
	여러 줄 주석 	
 */
 // 변수 선언
window.onload = function() {
	// main 함수
	let a = 10;
	let b = 10.5;
	let c = 'ABC';
	let d = "ABC";
	let e = [1, 2, 3, "홍길동", "남자"]; // 혼합 가능
	let f = {name : "심청이", age : 30};
	/*
		1. number : 정수, 실수
		2. String : 문자열('', "")
		3. Object : {} -> 객체, []
		4. boolean : true, false
					=> 0, 0.0 -> false
					=> 나머지는 true
		5. null : 값이 없는 상태
		6. undefined : 변수 선언이 없는 경우
		=> 데이터형 확인 : typeof
	*/
	
	// 도스창에 출력
	console.log("a = " + a);
	console.log("b = " + b);
	console.log("c = " + c);
	console.log("d = " + d);
	console.log("e = " + e);
	console.log("f = " + f);
	
	// 브라우저에 출력
	document.write("a = " + a + "<br>");
	document.write("b = " + b + "<br>");
	document.write("c = " + c + "<br>");
	document.write("d = " + d + "<br>");
	document.write("e = " + e + "<br>");
	document.write("f = " + f + "<br>");
	document.write("<hr>");
	document.write("<h3>변수의 데이터형 확인</h3>");
	document.write("a = " + typeof a + "<br>");
	document.write("b = " + typeof b + "<br>");
	document.write("c = " + typeof c + "<br>");
	document.write("d = " + typeof d + "<br>");
	document.write("e = " + typeof e + "<br>");
	document.write("f = " + typeof f + "<br>");
	document.write("i = " + typeof i + "<br>");
	document.write("<hr>");
	document.write("<h3>데이터형의 변경</h3>");
	let k = 10;
	document.write("k = " + typeof k + "<br>");
	k = "Hello";
	document.write("k = " + typeof k + "<br>");
	k = {msg : "Hello", len : 5};
	document.write("k = " + typeof k + "<br>");
	k = ["Hello", "JavaScript"];
	document.write("k = " + typeof k + "<br>");
	
	// Nan -> 연산처리가 안된 경우
	// null -> 배열이나 객체의 값이 없는 경우
}
</script>
</head>
<body>

</body>
</html>