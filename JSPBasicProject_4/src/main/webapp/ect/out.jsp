<%@page import="com.sist.dao.EmpVO"%>
<%@page import="java.util.List"%>
<%@page import="com.sist.dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	EmpDAO dao = EmpDAO.newInstance();
	List<EmpVO> list = dao.empListData();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JspWriter : 버퍼관리</h1>
	1. 버퍼 크기: <%= out.getBufferSize() %><br>
	2. 남아 있는 버퍼: <%= out.getRemaining() %><br>
	3. 사용 중인 버퍼: <%= out.getBufferSize() - out.getRemaining() %><br>
	4. 사용처 : 복잡한 HTML 이 있는 경우 사용
		<table border=1 bordercolor=black width=300>
			<tr>
				<td>HI</td>
			</tr>
			<tr>
				<td>
				<% for(int i = 1; i <= 2; i++) {
					out.println("&nbsp;&nbsp;");
				}
				%>
				Hello JSP2
				<%
					if(true) {
						out.println("<sup>new</sup>");
					}
				%>
				</td>
			</tr>
			<tr>
				<td width=>
				<%
					for(EmpVO vo : list) {
						out.println("empno: " + vo.getEmpno());
						out.println("ename: " + vo.getEname() + "<br>");
					}
				%>
				</td>
			</tr>
		</table>
</body>
</html>