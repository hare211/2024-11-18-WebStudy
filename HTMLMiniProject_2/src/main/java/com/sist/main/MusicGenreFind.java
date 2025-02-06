package com.sist.main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/MusicGenreFind")
public class MusicGenreFind extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 브라우저로 전송 HTML 을 시작한다
		response.setContentType("text/html;charset=UTF-8");
		// 2. 브라우저 연결
		PrintWriter out = response.getWriter();
		// 사용자가 보내준 값을 받는다
		String strPage = request.getParameter("page");
		if (strPage == null) {
			strPage = "1";
		}
		int curPage = Integer.parseInt(strPage);
		
		String cno = request.getParameter("cno");
		// Default 값 잡기(처음 접속했을 때)
		if (cno == null) {
			cno = "1";
		}
		
		MusicDAO dao = MusicDAO.newInstance();
		List<MusicVO> list = dao.getMusicGenreList(Integer.parseInt(cno), curPage);
		int totalPage = dao.getMusicGenreTotalPage(Integer.parseInt(cno));
		
		final int BLOCK = 10;
		int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
		
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("<link rel=\"stylesheet\" href=\"table.css\">");
		out.println("<link rel=stylesheet href=css/musictable.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=\"row text-center\">");
		out.println("<a href=MainServlet?mode=3&cno=1 class=\"btn btn-sm btn-danger\">Top50</a>");
		out.println("<a href=MainServlet?mode=3&cno=2 class=\"btn btn-sm btn-success\">가요</a>");
		out.println("<a href=MainServlet?mode=3&cno=3 class=\"btn btn-sm btn-info\">POP</a>");
		out.println("<a href=MainServlet?mode=3&cno=4 class=\"btn btn-sm btn-primary\">OST</a>");
		out.println("<a href=MainServlet?mode=3&cno=5 class=\"btn btn-sm btn-info\">트롯</a>");
		out.println("<a href=MainServlet?mode=3&cno=6 class=\"btn btn-sm btn-success\">JAZZ</a>");
		out.println("<a href=MainServlet?mode=3&cno=7 class=\"btn btn-sm btn-danger\">클래식</a>");
		
		
		out.println("</div>");
		out.println("<div class=row style=\"margin-top: 20px\">");

		out.println("<table>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th></th>");
		out.println("<th>곡명</th>");
		out.println("<th>가수</th>");
		out.println("<th>등락</th>");
		out.println("<tbody>");
		for (MusicVO vo : list) {
			//out.println("<div class=\"col-md-3\">");
			//out.println("<div class=\"thumbnail\">");
			out.println("<tr class=\"list\">");
			//out.println("<a href=\"MusicBeforeDetail?mno=" + vo.getMno() + "\">");
			out.println("<td><img src=" + vo.getPoster() + " style=\"width:48px;height:48px\"></td>");
			out.println("<td><a href=\"MusicBeforeDetail?mno=" + vo.getMno() + "\">" + vo.getTitle() + "</a></td>");
			out.println("<td>" + vo.getSinger() + "</td>");
			out.println("<td>" + vo.getIdcrement() + "&nbsp;&nbsp;" + vo.getState() + "</td>");
			out.println("</a>");
			out.println("</tr>");
			//out.println("</a>");
			//out.println("</div>");
			//out.println("</div>");
			
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("</div>");
		out.println("<div class=\"row text-center\">");
		out.println("<ul class=\"pagination\">");
		if (startPage > 1) {
			out.println("<li><a href=\"MainServlet?mode=3&cno=" + cno + "&page=" + (startPage - 1) + "\">&lt;</a></li>");
		}
		/*
		 * http://localhost:8080/HTMLFoodProject/FoodTypeFind?type=%EC%96%91%EC%8B%9D
		 * GET 일 때만 자동으로 decoding
		 * 
		 */
		for (int i = startPage; i <= endPage; i++) {
			if (i == curPage) {
				out.println("<li class=active><a href=\"MainServlet?mode=3&cno=" + cno + "&page=" + i + "\">" + i + "</a></li>");
			} else {
				out.println("<li><a href=\"MainServlet?mode=3&cno=" + cno + "&page=" + i + "\">" + i + "</a></li>");
			}
		}
		if (endPage < totalPage) {
			out.println("<li><a href=\"MainServlet?mode=3&cno=" + cno + "&page=" + (endPage + 1) + "\">&gt;</a></li>");
		}
		
		out.println("</ul>");
		
		
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
	/*
	 
	 for (MusicVO vo : list) {
			out.println("<div class=\"col-md-3\">");
			out.println("<div class=\"thumbnail\">");
			out.println("<a href=\"MusicBeforeDetail?mno=" + vo.getMno() + "\">");
			out.println("<img src=" + vo.getPoster() + " style=\"width:230px;height:150px\">"); 
			out.println("<div class=\"caption\">");
			out.println("<p>" + vo.getTitle() + "</p>");
			out.println("</div>");
			out.println("</a>");
			out.println("</div>");
			out.println("</div>");
		}
	 */

}
