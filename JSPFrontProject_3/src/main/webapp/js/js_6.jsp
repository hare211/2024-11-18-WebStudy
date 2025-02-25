<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	객체 표현
	let sawon = {} => 변수 / 함수
	형식) {sabun:1, name:"홍길동", dept:"개발부"}
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload = () => {
		let sawon = {
				sabun:1,
				name:"홍길동",
				dept:"개발부",
				job:"대리",
				pay:3500
		};
		document.write("사번: " + sawon.sabun + "<br>");
	};
	
	for (let key in sawon) {
		document.write(sawon[key] + "<br>");
	};
</script>
</head>
<body>

</body>
</html>