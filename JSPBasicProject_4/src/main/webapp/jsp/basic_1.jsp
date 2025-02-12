<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*, java.util.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    

<%
	EmpDAO dao = EmpDAO.newInstance();
	List<EmpVO> list = dao.empListData();
	request.setAttribute("list", list);
%>
    
<!-- 
	내장 객체 / 내부 객체 => 미리 객체를 생성한 후 사용
	1. request
		클래스명 => HttpServletRequest
		용도 => 클라이언트의 요청 값을 묶어서 저장(톰캣)
			   클라이언트 정보 확인(IP, PORT)
			   세션, 쿠키 생성
			   *** HttpSession session = request.getSession()
			   *** Cookie cookies = request.getCookie()
			   한글 변환 가능
			   데이터를 추가하는 기능
	    주요 메서드
	    	   클라이언트의 요청 값 가져오기
	    	   = getParameter("key") => 단일 데이터
	    	   = getParameterValues("key") => 다중 데이터
	    	   		=> checkbox
    	   	   = setCharacterEncoding("UTF-8")
    	   	   		=> 한글 깨짐 방지
   	   	   	   클라이언트 정보 확인(IP, PORT)
   	   	   	   = getRemoteAddr() : 접속자의 IP
   	   	   	   세션, 쿠키 생성
   	   	   	   HttpSession session = request.getSession()
   	   	   	   Cookie cookies = request.getCookie()
   	   	   	   = 서버 정보 => URL => MVC
   	   	   	   getRequestURL()
   	   	   	   *** getRequestURI()
   	   	   	   *** getContextPath() => 루트(프로젝트 명)
   	   	   	   
   	   	   	   추가정보 => JSTL / EL
   	   	   	   *** setAttribute(key, values)
   	   	   	   *** getAttribute(key)
  	2. response
  		클래스명 => HttpServletResponse
  		용도 : 전송(요청에 대한 처리 결과)
  			  --- HTML, Cookie
  			  response.setContentType("text/html")
  			  *** response.addCookie(cookie)
  			  화면 이동 : 서버에서 사용자의 화면을 변경하는 역할
  			  *** sendRedirect("이동할 파일명")
  			  헤더 관리
  			  setHeader() => 다운로드 시에 사용
    3. out(출력 버퍼 관리)
    	클래스명 : JspWriter
    			=> 출력 버퍼(HTML을 출력해주는 메모리 공간)
    				=> 브라우저가 읽어서 출력
    				   ---------------- 자동으로 메모리를 비워준다
    4. pageContext : 페이지 흐름
    	include / forward
    	=> <jsp:include>
    	=> <jsp:forward> : request 가 초기화되지 않는다
    	클래스명 : PageContext
    	용도 : 내장 객체 얻기
    		HttpServletRequest request = pageContext.getRequest();
    		getRequest(), getResponse(), getSession()
    		getPage(), getException()...
    		
 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
</head>
<body>
	<h3>사원 목록</h3>
	<table class="table_content" width=700>
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th>직위</th>
			<th>급여</th>
		</tr>
		<c:forEach var="vo" items="${list }">
			<tr>
				<td>${vo.empno }</td>
				<td>${vo.ename }</td>
				<td>${vo.job }</td>
				<td>${vo.sal }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>