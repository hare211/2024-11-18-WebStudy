<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	1. HTML 태그 읽기
		document : 문서 저장 공간
		document.getElementById("ID")
		document.getElementByTagName("태그명")
		document.getElementByClassName("className")
		document.querySelector("CSS")
							   -----
							   ID -> #아이디명
							   Class -> .클래스명
							   태그명 -> 태그명
							   선택자 -> 이벤트 처리 가능
   2. let 변수명 = [] : 배열
   		=> List list = new ArrayList()
   	  let 변수명 = {} : 객체
   	  			 -- JSON
   	  	=> MovieVO
   	  	
   	  	사용법
   	  	let sawon = {sabun:1, name:"홍길동", sex:"남자", dept:"개발부"}
   	  	sawon.sabun -> 1
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 900px;
}
</style>
<script type="text/javascript">
	// [] JSONArray
	// {} JSONObject
	const sawons = [
		{sabun:1, name:"홍길동", dept:"개발부", job:"대리", pay:3500},
		{sabun:2, name:"홍길동", dept:"개발부", job:"대리", pay:3500},
		{sabun:3, name:"홍길동", dept:"개발부", job:"대리", pay:3500},
		{sabun:4, name:"홍길동", dept:"개발부", job:"대리", pay:3500},
		{sabun:5, name:"홍길동", dept:"개발부", job:"대리", pay:3500},
	]
	
	window.onload = function() {
		let html = ""
		sawons.map(function(sawon) {
			html += '<li>' + (sawon.sabun + " " + sawon.name + " " + sawon.dept) + '</li>'
		})
		
		document.querySelector('ul').innerHTML = html
		// 어떤 태그에 첨부할 지 -> 객체 모델
		// document.querySelector('ul')
		//						$('ul')
	}
</script>

</head>
<body>
	<ul>
		
	</ul>
</body>
</html>