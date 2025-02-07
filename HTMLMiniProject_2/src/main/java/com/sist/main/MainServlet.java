package com.sist.main;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		// 사용자 요청 페이지를
		String mode = request.getParameter("mode");
		if (mode == null) {
			mode = "1"; // << 페이지 변경
		}
		String page = ChangeServlet.pageChange(Integer.parseInt(mode));
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js\"></script>");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");
		out.println("</head>");
		out.println("<body>");
		String html = "<nav class=\"navbar navbar-inverse\">"
				+ "  <div class=\"container-fluid\">"
				+ "    <div class=\"navbar-header\">"
				+ "      <a class=\"navbar-brand\" href=\"MainServlet\">MainPage</a>"
				+ "    </div>"
				+ "    <ul class=\"nav navbar-nav\">"
				+ "      <li class=\"active\"><a href=\"MainServlet\">Home</a></li>"
				+ "      <li class=\"dropdown\">"
				+ "        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">음악"
				+ "        <span class=\"caret\"></span></a>"
				+ "        <ul class=\"dropdown-menu\">"
				+ "          <li><a href=\"MainServlet?mode=3\">장르별 음악</a></li>"
				+ "          <li><a href=\"MainServlet?mode=4\">음악 검색</a></li>"
				+ "        </ul>"
				+ "      </li>"
//				+ "      <li class=\"dropdown\">"
//				+ "        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">음악"
//				+ "        <span class=\"caret\"></span></a>"
//				+ "        <ul class=\"dropdown-menu\">"
//				+ "          <li><a href=\"#\">음악 목록</a></li>"
//				+ "        </ul>"
//				+ "      </li>"
				+ "      <li><a href=\"#\">게시판</a></li>"
				+ "      <li><a href=\"#\">기타</a></li>"
				+ "    </ul>"
				+ "  </div>"
				+ "</nav>";
		out.println(html);
		/*
		  Session : 서버에 저장
		            보안이 뛰어나다
		            Object 단위로 저장
		  Cookie : 클라이언트에 저장
		  		   보안에 취약
		  		   String 단위로 저장
		 */
		HttpSession session = request.getSession();
		//session.setAttribute("id", "hong");
		//session.setAttribute("name", "hong");
		
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		out.println("<div class=container>");
		out.println("<div class=\"row text-right\">");
		if (id == null) { // 로그인 x
			
			out.println("<form method=post action=LoginServlet>");
			out.println("ID:");
			out.println("<input type=text name=id size=15 class=input-sm required>");
			out.println("Password:");
			out.println("<input type=text name=pwd size=15 class=input-sm required>");
			out.println("<input type=submit value=로그인 class=\"btn-sm btn-danger\">");
			
			out.println("</form>");
		} else { // 로그인 o
			out.println("<form method=get action=LoginServlet>");
			out.println(name + "님 로그인중입니다");
			out.println("<input type=submit value=로그아웃 class=\"btn-sm btn-success\">");
			out.println("</form>");
		}
		out.println("</div>");
		out.println("</div>");
		RequestDispatcher rd = request.getRequestDispatcher(page);
		
		rd.include(request, response);
		
		
		out.println("</body>");
		out.println("</html>");
	}

}
