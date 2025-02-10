<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	1) 웹 동작
		요청 / 응답
			  -- 서버(response) => C/S => 네트워크
		-- 클라이언트(request)
			=> 서버 연결 시 브라우저를 이용한다
			   --------- 브라우저에서 서버와 연결되는 부분
			   			 ------------------------ 주소창
   			=> 반드시 URL 을 이용할 때
   				http://ServerIP:PORT/ProjectName/FolderName/FileName
   				
   				파일명이 생략 가능한 파일 : welcome 파일
   				index.html / index.jsp
   				** 파일의 확장자는 개발자가 설정할 수 있다
   					.회사명(.naver, .daum, .do)
				나머지는 반드시 파일명을 첨부한다
				
				http://localhost/JSPBasicProject_1/jsp/basic_2.jsp => URL
				---------------- ---------------------------------
					서버 정보						URI
								 -----------------
								 	ContextPath
				 	------------------------------------------
				 					request
				 					
				사용자가 html/jsp/servlet 을 요청
				서버에서는 출력된 HTML 을 브라우저로 전송 실행
				
				JSP 를 활용하는 이유 : 동적 페이지
									한 개의 파일을 이용해서 데이터 변경 후 출력
									----------- 여러 개 처럼 사용 가능
				서블릿 => JSP 출력 용이
				
				서블릿 => HTML 출력 불편
						수정 시 마다 컴파일 필요
		구성요소
			브라우저 : 크롬, 파폭, IE
			웹서버 : Apache / IIS
			웹애플리케이션 : 자바 번역 / JSP 를 자바로 변경
						 => tomcat
			데이터베이스 : 오라클 / MySQL
							
		
				
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	URL : <%= request.getRequestURL() %><br>
	URI : <%= request.getRequestURI() %><br>
	ContextPath : <%= request.getContextPath() %><br>
	ServerInfo : <%= request.getServerName() %><br>
	ServerPORT : <%= request.getServerPort() %><br>
</body>
</html>