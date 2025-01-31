package com.sist.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.sist.dao.BoardDAO;

/**
 * Servlet implementation class BoardDelete
 */
/*
 	JSP 는 GET, POST 가 나눠지지 않음
 	=> delete.jsp, delete_ok.jsp 로 따로 만들어야 함
 	
 */
@WebServlet("/BoardDelete")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 삭제 폼 제작
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 브라우저로 전송할 방식
		response.setContentType("text/html;charset=UTF-8"); // 브라우저로 전송할 형식, 코드 지정 
		// text/xml, text/plain(JSON)
		// 2. HTML 을 읽을 브라우저 정보 response
		PrintWriter out = response.getWriter();
		// 3. 사용자가 보낸 요청 받기
		String no = request.getParameter("no");
		// 삭제 화면
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=css/table.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>삭제하기</h1>");
		out.println("<form method=post action=BoardDelete>"); // 자신 호출(doPost)
		out.println("<table class=table_content width=350>");
		out.println("<tr>");
		out.println("<td>");
		out.println("비밀번호<input type=password size=20 name=pwd required>");
		// hidden 데이터 감추기
		out.println("<input type=hidden name=no value=" + no + ">");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td align=center>");
		out.println("<input type=submit value=삭제>");
		out.println("<input type=button value=취소 onclick=javascript:history.back()>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

	// 삭제 관련 요청 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 삭제 처리 => 비밀번호 처리 => 간단한 JavaScript(alert)
		// 1. 브라우저로 전송할 방식
		response.setContentType("text/html;charset=UTF-8"); // 브라우저로 전송할 형식, 코드 지정 
		// text/xml, text/plain(JSON)
		// 2. HTML 을 읽을 브라우저 정보 response
		PrintWriter out = response.getWriter();
		// 3. 사용자가 보낸 데이터 받기
		String no = request.getParameter("no");
		String pwd = request.getParameter("pwd");
		
		// 오라클 연동
		BoardDAO dao = BoardDAO.newInstance();
		
		boolean bCheck = dao.boardDelete(Integer.parseInt(no), pwd);
		// 화면 이동 1-1. 비밀번호가 틀린 경우(alert)
		if (bCheck == false) {
			out.println("<script>");
			out.println("alert(\"비밀번호가 틀립니다\");");
			out.println("history.back();");
			out.println("</script>");
		} 
		// 화면 이동 1-2. 목록으로 이동
		else {
			out.println("<script>");
			out.println("alert(\"삭제되었습니다\");");
			out.println("location.href='BoardList';");
			out.println("</script>");
		}
		
		
	}

}
