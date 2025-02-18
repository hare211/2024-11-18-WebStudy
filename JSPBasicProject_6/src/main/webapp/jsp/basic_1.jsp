<%@page import="com.sist.dao.EmpBean"%>
<%@page import="java.util.List"%>
<%@page import="com.sist.dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*" %>
<jsp:useBean id ="dao" class="com.sist.dao.EmpDAO"/>    
<%
	List<EmpBean> list = dao.empListData();
%>    

<%-- 

	1. 빈즈 / 액션
		JSP => Bean => VO(Spring), DTO(MyBatis)
						=> Spring : Java => 화면 출력(JSP)
		
		<jsp:useBean>: 객체 메모리 할당
			MemberVO vo = new MemberVO()
			<jsp:useBean id="vo" class="MemberVO"/>
		<jsp:setProperty>: 객체 변수 선언
			vo.setName("aaa")
			<jsp:setProperty name="vo" property="name" value="aaa">
			<jsp:setProperty name="vo" property="*">
				=> vo 가 가지고 있는 모든 setter 에 값을 채운다
			** 스프링
				public String memberInsert(MemberVO vo)
										  ------------- command 객체
				
		<jsp:getProperty>: 객체 변수 출력
		<jsp:param>: 다른 페이지 이동 시 필요한 매개변수 전송
	2. DBCP : DataBase Connection Pool
		a. 데이터베이스 연결에 소모되는 시간을 단축시킬 수 있다
		b. 미리 Connection 을 데이터베이스에 연결한 상태에서 저장
		c. 일반적으로 웹 프로그램은 대부분 DBCP 를 사용한다
		d. Connection 의 객체 생성 제한
		e. 사용 후 재사용
		f. Connection 을 미리 생성 했기 때문에 객체관리 용이
		g. 쉽게 서버가 다운되지 않는다
		<dataSource type="POOLED"> MyBatis 에서 DBCP
		목적 : 웹 사용자에게 응답시간 단축
			  효울적인 데이터베이스 연동
			  
	  	DBCP
		  	방법
		  		1. 시작과 동시에 Connection 객체 생성(연결된 상태)
		  			몇 개 생성
		  				maxIdel="10"
		  			만약에 초과되면 확장
		  				추가 maxActive="10"
		  		2. 저장
		  			저장 공간(Pool)
	  			3. 사용자가 요청하면 Connection 의 주소를 얻어온다
	  			4. 사용자 요청 처리
	  			5. 사용 후 반드시 POOL 에 반환(재사용)
  			코딩
  				1. server.xml 등록
  				2. getConnection() => 미리 생성된 Connection 객체 얻기
  				3. disConnection() => 반환
  				4. 기능은 동일
  			
  			
	3. Cookie / Session
	4. JSTL / EL
	5. MVC => Spring
	
 --%>    
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
</head>
<body>
	<h3>사원 목록</h3>
	<table class="table_content" width=500>
		<tr>
			<th>사원</th>
			<th>이름</th>
			<th>직위</th>
			<th>입사일</th>
			<th>급여</th>
		</tr>
		<%-- 출력 위치 --%>
		<%
			for(EmpBean vo:list) {
		%>
		<tr>
			<td><%= vo.getEmpno() %></td>
			<td><%= vo.getEname() %></td>
			<td><%= vo.getJob() %></td>
			<td><%= vo.getHiredate() %></td>
			<td><%= vo.getSal() %></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>