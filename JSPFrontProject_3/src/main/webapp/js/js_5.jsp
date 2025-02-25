<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	여러 개의 데이터를 묶어서 관리
	배열 / 객체
	[] {} => JSON(JavaScript Object Nontation)
			 자바스크립트 객체 표현법
	=> 배열(Array): 혼합이 가능 -> let
	Object[] obj = {1, "", ''...}
	let names = ["", "", ""];
	let sawon = [1, "이름", 90, 90, 90, 180, 90.0];
	
	지원하는 함수
		1. 추가: sawon[0] = 2
				push()
		2. 삭제: pop()
		3. 개수: length
		4. 자르기: slice() -> arraycopy
		5. 찾기: find
		6. 완전삭제: delete
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload = () => {
		let names =  ["홍길동", "심청이", "박문수"];
		console.log(names);
		console.log("인원: " + names.length);
		
		// 데이터 추가
		names.push("이순신");
		console.log(names);
		console.log("인원: " + names.length);
		
		// 데이터 삭제
		names.pop(); // 마지막 제거
		console.log(names);
		console.log("인원: " + names.length);
		
		 // 첫 번째에 추가
		names.unshift("ㅇㅇㅇ");
		console.log(names);
		console.log("인원: " + names.length);
		
		console.log(names.slice(2)); // start => start 포함 start 이후 값
		console.log(names);
		console.log("2 인원: " + names.length);
		console.log(names.slice(1, 2));
		console.log(names);
		console.log("인원: " + names.length);
		console.log(names.slice(1, 3 + 1)); // slice -> start, end => start 포함, end 미포함
		console.log(names);
		console.log("3 + 1 인원: " + names.length);
		
		let names2 = [...names];
		console.log(names2);
		
	};
</script>
</head>
<body>

</body>
</html>