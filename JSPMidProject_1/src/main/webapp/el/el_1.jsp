<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	EL : 표현식(브라우저에 데이터 출력) <%= %>
		 ${출력할 데이터}
	JSTL : 태그형 라이브러리 => 자바의 제어문, 변수선언, 화면이동, 날짜변환, 숫자변환
	JSP : 태그형 라이브러리 => 자바를 사용하지 않는다
			HTML + Java
			| 결과값만 출력(Front)
						| 자바스크립트 => Jquery
						  | Ajax / Axios
    구조 : 자바에서 전송 => JSTL + EL => 화면출력
    
    동작		 요청
    	JSP ====> Java ====> 요청 처리 ====> JSP
    						 |			   |
    						 ---------------
    						 request / session 에 값을 담는다
    						 -----------------
    						 |${} => request.getAttribute
    						 |setAttribute()
	출력시
	연산처리 => 조건, 출력(EL)
		산술연산자 : +, -, *, /, %
			
		비교연산자
		논리연산자
		삼항연산자
	제어문(JSTL) 
		*** core : 제어문, 변수 선언, 화면 이동
			=> import : uri="jakarta.tags.core"
			
			제어문
				조건문 :
					<c:if test="${조건문(논리/비교)}">
						HTML 코드
					</c:if>
				선택문 : 다중 조건문 사용
					<c:choose>
						<c:when test(조건)=""></c:when>
						<c:when test(조건)=""></c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				반복문 :
					for (int i=1; i <=10; i++)
					<c:forEach var="i" begin="1" end="10" step="1"> step 1 인 경우 생략 가능
					</c:forEach>
					
					for(MovieVO vo:list)
					<c:forEach var="vo" items="${list}">
					</c:forEach>
					
					<c:forTokens> -> StringTokenizer
			변수선언 :
				<c:set var="id" value="hong">
				String id="hong" (X)
				request.setAttribute("id", "hong") (O)
			화면이동:
				response.sendRedirect("파일명")
				<c:redirect url="파일명">
		*** fmt : 날짜 변환, SimpleDateFormat => yyyy-mm-dd
				  숫자 변환, DecimalFormat => 천단위
	    *** fn : String 클래스의 메서드 호출 => uri="jakarta.tags.functions"
	    			length(), substring()
 --%>    
 <%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <%--
 	core : 제어문 / 변수 선언 / 화면 이동
 	prefix="c" => <c:forEach>
 	prefix="core" => <core:forEach>
  --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>일반 자바 for 문</h3>
	<%
		for(int i = 1; i <= 10; i++) {
	%>
		<%= i %>&nbsp;
	<%
		}
	%>
	<h3>&lt;c:forEach 문&gt;</h3>
	<c:forEach var="i" begin="1" end="10" step="1">
		${i }&nbsp;
	</c:forEach>
</body>
</html>