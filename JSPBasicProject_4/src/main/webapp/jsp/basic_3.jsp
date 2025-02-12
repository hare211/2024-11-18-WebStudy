<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--
	* request / * response / * out / pageContext
	* application / * session
	config / page / exception
	|		 |		|
	web.xml	 this	try ~ catch
	
	application
		클래스명: SerlvetContext
		용도: 서버 관리
				3.5버전
				버전: getMajorVersion()
					 getMinorVersion()
					 => 서블릿 버전
				서버명: getServerInfo()
				로그파일
					: log()
					: web/xml 연동 => getInitParameter()
				*** 실제 경로명
					: getRealPath()
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
MajorVersion: <%= application.getMajorVersion() %><br>
MinorVersion: <%= application.getMinorVersion() %>
</body>
</html>