package com.sist.main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sist.dao.MusicDAO;
import com.sist.vo.MusicVO;

/**
 * Servlet implementation class MusicFind
 */
@WebServlet("/MusicFind")
public class MusicFind extends HttpServlet {

	
	
	
	 
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				
				String column = request.getParameter("column");
				String fd = request.getParameter("fd");
				
				MusicDAO dao = MusicDAO.newInstance();
				
				List<MusicVO> list = dao.musicFind(curPage, column, fd); // col 컬럼명, fd 검색어
				List<MusicVO> defaultList = dao.musicListData(curPage);
				
				int totalPage = dao.musicFindTotalPage(column, fd);
				int totalDefaultPage = dao.getTotalPage();
				
				final int BLOCK = 10;
				
				int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
				int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
				
				if (endPage > totalPage) {
					endPage = totalPage;
				}
				
				out.println("<html>");
				out.println("<head>");
				out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
				out.println("<link rel=stylesheet href=css/Music.css>");
				out.println("<link rel=\"stylesheet\" href=\"table.css\">");
				out.println("<link rel=stylesheet href=css/musictable.css>");
				out.println("</head>");
				out.println("<body>");
				out.println("<div class=container>");
				out.println("<div class=\"row\">");
				out.println("<form method=post action=MainServlet?mode=4>");
				out.println("<select name=column class=input-sm>");
				out.println("<option value=title>곡명</option>");
				out.println("<option value=singer>가수</option>");
				out.println("</select>");
				out.println("<input type=text name=fd size=15 class=input-sm>");
				out.println("<button class=\"btn-sm btn-danger\">검색</button>");
				out.println("</form>");
				
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
				if (column != null) { // if (list != null) {
					
					for (MusicVO vo : list) {
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
						
						/*
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
						*/
					}
				} else {
					for (MusicVO vo : defaultList) {
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
				}
				out.println("</tbody>");
				out.println("</table>");
				out.println("</div>");
				
				out.println("</div>");
				out.println("<div class=\"row text-center\">");
				out.println("<ul class=\"pagination\">");
				
				if (column != null) {
					
					if (startPage > 1) { // < 부분
						out.println("<li><a href=\"MainServlet?mode=4&column=" + column + "&fd=" + fd + "&page=" + (startPage - 1) + "\">&lt;</a></li>");
					}
					
					// http://localhost:8080/HTMLMusicProject/MusicTypeFind?type=%EC%96%91%EC%8B%9D
					// GET 일 때만 자동으로 decoding
					 
					
					for (int i = startPage; i <= endPage; i++) {
						if (i == curPage) {
							out.println("<li class=active><a href=\"MainServlet?mode=4&column=" + column + "&fd=" + fd + "&page=" + i + "\">" + i + "</a></li>");
						} else {
							out.println("<li><a href=\"MainServlet?mode=4&column=" + column + "&fd=" + fd + "&page=" + i + "\">" + i + "</a></li>");
						}
					}
					if (endPage < totalPage) { // > 부분
						out.println("<li><a href=\"MainServlet?mode=4&column=" + column + "&fd=" + fd + "&page=" + (endPage + 1) + "\">&gt;</a></li>");
					}
				} else {
					startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
					endPage = startPage + BLOCK - 1;
					if (endPage > totalDefaultPage) {
						endPage = totalDefaultPage;
					}
					
					if (startPage > 1) {
						out.println("<li><a href=\"MainServlet?mode=4&page=" + (curPage - 1) + "\">&lt;</a></li>");
					}
					
					for (int i = startPage; i <= endPage; i++) {
						if (i == curPage) {
							out.println("<li class=active><a href=\"MainServlet?mode=4&page=" + i + "\">" + i + "</a></li>");
						} else {
							out.println("<li><a href=\"MainServlet?mode=4&page=" + i + "\">" + i + "</a></li>");
						}
					}
					if (curPage < totalDefaultPage) {
						out.println("<li><a href=\"MainServlet?mode=4&page=" + (curPage + 1) + "\">&gt;</a></li>");
					}
				}
				
				out.println("</ul>");
				
				
				out.println("</div>");
				out.println("</div>");
				out.println("</body>");
				out.println("</html>");
	}
	
	/*
	 
	 	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				
				String column = request.getParameter("column");
				String fd = request.getParameter("fd");
				
				MusicDAO dao = MusicDAO.newInstance();
				List<MusicVO> list = dao.musicFind(curPage, column, fd);
				int totalPage = dao.musicFindTotalPage(column, fd);
				
				final int BLOCK = 10;
				int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
				int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
				
				if (endPage > totalPage) {
					endPage = totalPage;
				}
				
				out.println("<html>");
				out.println("<head>");
				out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
				out.println("<link rel=stylesheet href=css/Music.css>");
				out.println("</head>");
				out.println("<body>");
				out.println("<div class=container>");
				out.println("<div class=\"row\">");
				out.println("<form method=post action=MainServlet?mode=4>");
				out.println("<select name=column class=input-sm>");
				out.println("<option value=title>곡명</option>");
				out.println("<option value=singer>가수</option>");
				out.println("</select>");
				out.println("<input type=text name=fd size=15 class=input-sm>");
				out.println("<button class=\"btn-sm btn-danger\">검색</button>");
				out.println("</form>");
				
				out.println("</div>");
				out.println("<div class=row style=\"margin-top: 20px\">");
				
				if (list != null) {
					
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
				}
				out.println("</div>");
				out.println("<div class=\"row text-center\">");
				out.println("<ul class=\"pagination\">");
				if (startPage > 1) {
					out.println("<li><a href=\"MainServlet?mode=4&column=" + column + "&fd=" + fd + "&page=" + (startPage - 1) + "\">&lt;</a></li>");
				}
				
				// http://localhost:8080/HTMLMusicProject/MusicTypeFind?type=%EC%96%91%EC%8B%9D
				// GET 일 때만 자동으로 decoding
				//
				 
				for (int i = startPage; i <= endPage; i++) {
					if (i == curPage) {
						out.println("<li class=active><a href=\"MainServlet?mode=4&column=" + column + "&fd=" + fd + "&page=" + i + "\">" + i + "</a></li>");
					} else {
						out.println("<li><a href=\"MainServlet?mode=4&column=" + column + "&fd=" + fd + "&page=" + i + "\">" + i + "</a></li>");
					}
				}
				if (endPage < totalPage) {
					out.println("<li><a href=\"MainServlet?mode=4&column=" + column + "&fd=" + fd + "&page=" + (endPage + 1) + "\">&gt;</a></li>");
				}
				
				out.println("</ul>");
				
				
				out.println("</div>");
				out.println("</div>");
				out.println("</body>");
				out.println("</html>");
	}
	 */

}
