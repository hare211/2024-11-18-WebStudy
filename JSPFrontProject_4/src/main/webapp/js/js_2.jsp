<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	내장 객체
	-------
	window: 브라우저 자체 제어
			open
			close
	document: write
			  querySelector()
    history: back(), go()
    location: href="이동할 파일명"
    
    데이터형
    -----
    	Number -> 형변환 / parseInt()
    	------ toLocaleString()
    	String
    		length(): 문자 개수 -> 비밀번호 유효성 검사
    		indexOf() / lasIndexOf(): 문자 위치 찾기
    		replace(): 문자 변경
    		split(): 문자 분리 -> 배열로 저장
    		substring(): 문자 자르기
    		substr(number, number)
    					   개수
    			   시작 인덱스 번호
		    trim(): 좌우 공백 제거
    	Array
    		push(): 배열에 데이터 삽입(맨끝)
    		pop(): 데이터 삭제(맨끝)
    		slice(): 원하는 위치의 데이터를 잘라 새로운 배열 생성
    		length(): 데이터 개수
    	Date
    		let today = new Date()
    		today.getFullYear(): 연도
    		today.getMonth(): 월 -> 0부터 시작 오늘 날짜 가져올 시 + 1
    		today.getDate(): 일
    		today.getDay(): 요일
    	Math
    		round(): 반올림
 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	onload = function() {
		let today = new Date();
		let year = today.getFullYear();
		let month = today.getMonth() + 1;
		let date = today.getDate();
		let day = today.getDay();
		
		let strWeek = ["일", "월", "화", "수", "목", "금", "토"];
		document.write("오늘은 " + year + "년" + month + "월" + date + "일 " + strWeek[day] + "요일입니다");
	}
</script>
</head>
<body>

</body>
</html>