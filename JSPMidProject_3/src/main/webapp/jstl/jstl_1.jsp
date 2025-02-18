<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	JSTL
		=> JSP Standard Tag Library
		=> JS 표준 태그 라이브러리
	---------------------------------
	core : 일반 프로그램에서 사용 되는 변수 / 제어문 / 화면 이동
			<c:set var="변수명" value="값">
			=> request.setAttribute("변수", 값)
			<c:out value="값">
			=> out.println("값")
			=> JQuery => $(), %{}:EL
			   --------------------- javascript 영역
		   제어문
		   	<c:if test="condition">
		   		문장
		   	</c:if>
		   	예)
		   		<c:if test="${id == "admin"}">
		   		</c:if>
	   		
	   		<c:forEach var="i" begin="1" end="10" step="1">
	   			반복 수행 문장
	   		</c:forEach>
	   		
	   		<c:forTokens var="변수명" items="값" delims="구분자">
	   			StringTokenizer st = new StringTokenizer(값, 구분자)
	   		</c:forTokens>
	   		
	   	   다중조건문
	   	   	<c:choose>
	   	   		<c:when test="조건문">출력문</c:when>
	   	   		<c:when test="조건문">출력문</c:when>
	   	   		<c:when test="조건문">출력문</c:when>
	   	   		<c:otherwise></c:otherwise>
	   	   	</c:choose>
   	   	   
   	   	   화면이동
   	   	   	<c:redirect  url="이동할파일명">
   	   	   	=> response.sendRedirect()
   	   	   
   	   	   *** JSP 안에서는 자바코딩을 하지 않는다
	format
		<fmt:formatDate> -> SimpleDateFormat
			request.setAttribute("today", new Date())
			<fmt:formatDate value="${today}" pattern="yyyy-MM-dd">
		<fmt:formatNumber> -> DecimalFormat
			request.setAttribute("price", 30000)
			<fmt:formatNumber value="${price}" pattern="###,###">
	functions : String 이 가지고 있는 메서드만 지원
		request.setAtribute("msg", "홍길동입니다")
		<c:set var="msg" value="홍길동입니다">
		${fn:length(msg)} -> 6
		${fn:substring(msg, 1, 3)}
		${fn:replace(msg, '홍', '김')}
	sql / xml => 자바 사용(보안, 로직 문제 때문에 거의 사용 X) 
	
	<%@ taglib prefix="c" uri="jakarta.tags.core" %>
	<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
	<%@ taglib prefix="fn" uri="jakarta.tags.functuons" %>
 --%>    
 <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>일반 자바</h3>
	<%
		for (int i = 1; i <= 10; i++) {
			if (i % 2 == 0){
	%>
			<%= i %>%nbsp;
	<%
			}
		}
	%>
	<h3>JSTL</h3>
		<c:forEach var="i" begin="1" end="10" step="1">
			<c:if test="${i % 2 == 0 }">
				${i }&nbsp;
			</c:if>
		</c:forEach>
	<h3>다중 조건문</h3>
		<%
			int star = 0;
		%>
		<%
			if (star == 0) {
				out.println("<span style=\"color:orange\">★★★☆☆</span>");				
			} else if(star == 1) {
				
			} else if(star == 2) {
				
			} else if(star == 3) {
				
			} else if(star == 4) {
				
			} else if(star == 5) {
				
			}
		%>
	<h3>JSTL 다중 조건문</h3>
		<c:set var="star" value="3"/>
			<c:choose>
				<c:when test="${star == 0 }">00000</c:when>
				<c:when test="${star == 1 }">a0000</c:when>
				<c:when test="${star == 2 }">aa000</c:when>
				<c:when test="${star == 3 }">aaa00</c:when>
				<c:when test="${star == 4 }">aaaa0</c:when>
				<c:when test="${star == 5 }">aaaaa</c:when>
			</c:choose>
	<h3>JSTL 선택 조건문</h3>
		<c:set var="sex" value="1"/>
			<c:choose>
				<c:when test="${sex == 1 }">남자</c:when>
				<c:otherwise>여자</c:otherwise>
			</c:choose>
</body>
</html>