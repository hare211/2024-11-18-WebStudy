package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.io.*;
import jakarta.servlet.http.Cookie;
import com.sist.dao.*;
import com.sist.vo.*;


@WebServlet("/FoodList")
public class FoodList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 브라우저로 전송 HTML 을 시작한다
		response.setContentType("text/html;charset=UTF-8");
		// 2. 브라우저 연결
		PrintWriter out = response.getWriter();
		
		// 3. 출력 전에 오라클 데이터 읽기
		FoodDAO dao = FoodDAO.newInstance();
		// 4. 사용자 요청 데이터 받기
		String page = request.getParameter("page");
		
		if (page == null) {
			page = "1";
		}
		// 현재 페이지 설정
		int curPage = Integer.parseInt(page);
		// 현재 페이지에 대한 데이터 읽기
		List<FoodVO> list = dao.foodListData(curPage);
		// 총 페이지 읽기
		int totalPage = dao.getTotalPage();
		// 블록별 페이지
		final int BLOCK = 10;
		/*
		 * 페이지수가 변경될 때 마다 페이지를 유지 또는 변경해야 함
		 */
		int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
		
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=row>");
		for (FoodVO vo : list) {
			out.println("<div class=\"col-md-3\">");
			out.println("<div class=\"thumbnail\">");
			out.println("<a href=\"FoodBeforeDetail?fno=" + vo.getFno() + "\">");
			out.println("<img src=" + vo.getPoster() + " style=\"width:230px;height:150px\">"); 
			out.println("<div class=\"caption\">");
			out.println("<p>" + vo.getName() + "</p>");
			out.println("</div>");
			out.println("</a>");
			out.println("</div>");
			out.println("</div>");
		}
		out.println("</div>");
		out.println("<div class=\"row text-center\">");
		out.println("<ul class=\"pagination\">");
		if (startPage > 1) {
			out.println("<li><a href=\"FoodList?page=" + (startPage - 1) + "\">&lt;</a></li>");
		}
		
		for (int i = startPage; i <= endPage; i++) {
			if (i == curPage) {
				out.println("<li class=active><a href=\"FoodList?page=" + i + "\">" + i + "</a></li>");
			} else {
				out.println("<li><a href=\"FoodList?page=" + i + "\">" + i + "</a></li>");
			}
		}
		if (endPage < totalPage) {
			out.println("<li><a href=\"FoodList?page=" + (endPage + 1) + "\">&gt;</a></li>");
		}
		
		out.println("</ul>");
		out.println("</div>");
		
		out.println("<div class=row>");
		out.println("<h3>최근 방문 맛집</h3>");
		out.println("<hr>");
		List<FoodVO> cList = new ArrayList<FoodVO>();
		Cookie[] cookies = request.getCookies();    
		if (cookies != null) {
			for (int i = cookies.length - 1; i >= 0; i--) { // 최신순으로
				// 키 : getName, 값 : getValue
				if (cookies[i].getName().startsWith("food_")) {
					String fno = cookies[i].getValue();
					FoodVO vo = dao.foodCookieData(Integer.parseInt(fno));
					cList.add(vo);
				}
			}
		}
		for (int i = 0; i < cList.size(); i++) {
			FoodVO cvo = cList.get(i);
			if (i > 8) {
				break;
			}
			out.println("<a href=FoodDetail?fno=" + cvo.getFno() + ">");
			out.println("<img src=" + cvo.getPoster() + " style=\"width:100px; height:85px; margin-bottom:25px;\" class=img-rounded title=" + cvo.getName() + ">");
			out.println("</a>");
			
		}
		out.println("</div>");
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
