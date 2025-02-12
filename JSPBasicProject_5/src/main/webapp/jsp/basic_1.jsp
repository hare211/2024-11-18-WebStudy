<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	1. 실행 과정
		브라우저 URL 요청	자바파일 변경	컴파일	인터프리터
		a.jsp => a_jsp.java => a_jsp.class => 메모리에 HTML 저장 => 브라우저에서 읽어서 출력
		
	2. page 지시자
		jsp 에 대한 정보를 가지고 있다
		contentType : 변환(html, xml, json)
		import : 라이브러리 추가
		errorPage : 에러 시 보여주는 jsp
		buffer => 8kb
	3. 자바 / HTML 분리
		<% %> => 일반 자바 코딩
		<%= %> => 데이터 출력
		
	4. 내장 객체
		request
			getParameter()
			getParameterValues()
			getSession()
			getCookies()
			getAttribute()
			getAttribute
			getRequestURI()
			getContextPath()
			getRemoteAddr()
		response
			setHeader()
			sendRedirect()
		application
			log()
			getInitParameter()
			getRealPath()
		out
			println() / print()
		pageContext : 내장 객체 관리 => JSP 연결 => 웹 흐름 제어
			<jsp:include>
			<jsp:forward>
		-----------
		session / cookie => 저장 => 데이터 유지
		
		
 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>