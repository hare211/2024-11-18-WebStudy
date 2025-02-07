package com.sist.main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/MusicList")
public class MusicList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 브라우저로 전송 HTML 을 시작한다
		response.setContentType("text/html;charset=UTF-8");
		// 2. 브라우저 연결
		PrintWriter out = response.getWriter();
		
		// 3. 출력 전에 오라클 데이터 읽기
		MusicDAO dao = MusicDAO.newInstance();
		// 4. 사용자 요청 데이터 받기
		String page = request.getParameter("page");
		
		if (page == null) {
			page = "1";
		}
		// 현재 페이지 설정
		int curPage = Integer.parseInt(page);
		// 현재 페이지에 대한 데이터 읽기
		List<MusicVO> list = dao.musicListData(curPage);
		// 총 페이지 읽기
		int totalPage = dao.getTotalPage();
		// 블록별 페이지
		final int BLOCK = 10;
		/*
		 * 페이지수가 변경될 때 마다 페이지를 유지 또는 변경해야 함
		 */
		int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
		int endPage = startPage + BLOCK - 1;
		
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">");
		out.println("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>");
		out.println("<link href=\"https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap\" rel=\"stylesheet\">");
		out.println("<link rel=\"stylesheet\" href=\"table.css\">");
		out.println("<link rel=stylesheet href=css/musictable.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container-fluid>");
		out.println("<div class=row>");
		out.println("<table>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th></th>");
		out.println("<th></th>");
		out.println("<th></th>");
		out.println("<th></th>");
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
			out.println("<li><a href=\"MainServlet?page=" + (curPage - 1) + "\">&lt;</a></li>");
		}
		
		for (int i = startPage; i <= endPage; i++) {
			if (i == curPage) {
				out.println("<li class=active><a href=\"MainServlet?page=" + i + "\">" + i + "</a></li>");
			} else {
				out.println("<li><a href=\"MainServlet?page=" + i + "\">" + i + "</a></li>");
			}
		}
		if (curPage < totalPage) {
			out.println("<li><a href=\"MainServlet?page=" + (curPage + 1) + "\">&gt;</a></li>");
		}
		
		out.println("</ul>");
		out.println("</div>");
		
		
		
		
		
		out.println("<div class=row>");
		out.println("<h3>최근 방문 곡</h3>");
		out.println("<hr>");
		List<MusicVO> cList = new ArrayList<MusicVO>();
		Cookie[] cookies = request.getCookies();    
		if (cookies != null) {
			for (int i = cookies.length - 1; i >= 0; i--) { // 최신순으로
				// 키 : getName, 값 : getValue
				if (cookies[i].getName().startsWith("music_")) {
					String mno = cookies[i].getValue();
					MusicVO vo = dao.musicCookieData(Integer.parseInt(mno));
					cList.add(vo);
				}
			}
		}
		for (int i = 0; i < cList.size(); i++) {
			MusicVO cvo = cList.get(i);
			if (i > 8) {
				break;
			}
			out.println("<a href=\"MainServlet?mode=2&mno=" + cvo.getMno() + "\">");
			out.println("<img src=" + cvo.getPoster() + " style=\"width:100px; height:85\" class=img-rounded title=" + cvo.getTitle() + ">");
			out.println("</a>");
			
		}
		out.println("</div>");
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
		
		/*
		 * 		for (MusicVO vo : list) {
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
		강
		 */
		/*
		 
		 		for (MusicVO vo : list) {
			out.println("<div class=\"col-md-12 mb-3\">");
			out.println("<div class=\"thumbnail d-flex align-items-center\">");
			out.println("<a href=\"MusicBeforeDetail?mno=" + vo.getMno() + "\">");
			out.println("<img src=" + vo.getPoster() + " style=\"width:180px;height:80px; margin-left: 10px;\">"); 
			out.println("<div class=\"caption\">");
			out.println("<p class=\"mb-0\">" + vo.getTitle() + "</p>");
			out.println("</div>");
			out.println("</a>");
			out.println("</div>");
			out.println("</div>");
		}
		 */
		
	}

}
