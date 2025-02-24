<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	ES6 => 변경(연산자)
		   비교연산자
		   ======= VueJS / ReactJS
		   같다 : == (경고) ===
		   같지 않다 : != (경고) !==
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = function() {
	let a = "Hello";
	let b = "JavaScript";
	
	console.log("a === b : " + (a === b));
	console.log("a !== b : " + (a !== b));
	console.log("a < b : " + (a < b));
	console.log("a > b : " + (a > b));
	console.log("a <= b : " + (a <= b));
	console.log("a >= b : " + (a >= b));
}
function send() {
	let frm = document.frm;
	/*
		window
		  |
	   document
	   	  |
	   	 form
	*/
	if (frm.id.value === "") {
		alert("아이디를 입력하세요");
		frm.id.focus();
		return;
	}
	if (frm.pwd.value === "") {
		alert("비밀번호를 입력하세요");
		frm.pwd.focus();
		return;
	}
	alert("ID: " + frm.id.value + "\n" + "Password: " + frm.pwd.value);
}
</script>
</head>
<body>
	<form name="frm">
		ID: <input type=text name=id size=10><br>
		PW: <input type=password name=pwd size=10><br>
		<input type=button value="전송" onclick="send()">
	</form>
</body>
</html>