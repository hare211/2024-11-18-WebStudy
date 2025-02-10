<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="" buffer="16kb"%>
<%--
	JSP : Java Server Page(서버에서 실행되는 자바 파일)
		: Back-End
		: JSP(Servlet 을 쉽게 작성할 수 있게 만든 파일)
			  => 영역 : doGet / doPost
			  => doGet, doPost 영역임
			  => JSP 는 View 역할
			  => Front-End 로 전향
		: Servlet(보안, 라이브러리, 연결)
				       ------- 스프링
		
		Back-End : Java / Oracle / Servelt / Spring
		Front-End : HTML / CSS / JavaScript / JSP
		
	1. HTML + 자바
		자바를 코딩하는 영역 : 스크립트
		<% %> : 스크립트릿 
			  : 일반 자바 => 메서드 호출 / 변수 설정 / 제어문...
		<%= %> : 표현식 => out.println()
				 데이터 출력
		<%! %> : 전역변수, 메서드 제작
				 선언문
	2. JSP 사용법
		지시자
			page : JSP 에 대한 정보를 저장
					1) 변환 : 브라우저 출력 시 어떤 형식을 출력할 지 지정
						html => text/html
						xml => text/xml
						json => text/json
						자바 스크립트 객체 표현법
							JavaScript Object Nontation
							자바 : VO => 자바 스크립트에서는 VO 인식 불가
							VO => {변수:값...}
							let number={} : Ojbect
							[] : List(Array)
					2) 라이브러리 읽기 : import
					3) 한글 변환 : pageEncoding
					4) 에러페이지 : errorPage
			taglib : 자바 / HTML 을 분리한 경우 주로 사용
						JSTL / EL
			
			<%
				for() {
			%>
				HTML
			<%
				}
			%>
			
			<c:forEach>
				<html> ${}
			</c:forEach>
			include
			  
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
		int a = 10;
		public void display(){}
	%>
</body>
</html>