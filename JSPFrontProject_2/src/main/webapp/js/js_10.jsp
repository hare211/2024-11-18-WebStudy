 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%--
	for-each
		= for in
			for (변수 in 배열)
				------------
				배열의 인덱스 번호 읽기(0부터)
				=> 배열 여러 개를 동시에 출력할 때
		= for of
			for (변수 of 배열)
				 --- 배열의 값을 1 개씩 읽어올 때
		= forEach
			배열.forEach(function(변수){})
								---- 배열의 값을 1 개씩 읽어온다
		= map
			배열.map(function(변수){})
							---- 배열의 값을 1 개씩 읽어온다
			배열.map((변수)=>{})
						--- function 을 제거
 --%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = () => {
	const names = ("ㅇㅇㅇ", "ㅂㅂㅂ", "ㅈㅈㅈ", "ㅌㅌㅌ", "ㅋㅋㅋ");
	const sexs = ("남자", "여자", "남자", "남자", "여자");
	
	document.write("<h3>일반 for</h3>");
	
	for(let i = 0; i < names.length; i++) {
		document.write(names[i] + "<br>")
	}
	document.write("<hr>")
	document.write("<h3>for-in(인덱스)</h3>")
	for(let index in names) {
		document.write(names[index] + "(" + sexs[index] + ")<br>")
	}
	document.write("<hr>")
	document.write("<h3>for-of(for-each)</h3>")
	// => 배열에서 데이터를 한 개씩 읽어오는 for
	for (let name of names) {
		document.write(name + "<br>")
	}
	document.write("<hr>")
	document.write("<h3>forEach 함수</h3>")
	names.forEch(function(name) {
		document.write(name + "<br>")
	})
	document.write("<hr>")
	document.write("<h3>map 함수</h3>")
	names.map(function(name){
		document.write(map + "<br>")
	})
	document.write("<hr>")
	document.write("<h3>map 함수</h3>")
	names.map((name) => {
		document.write(map + "<br>")
	})
}
</script>
</head>
<body>

</body>
</html>