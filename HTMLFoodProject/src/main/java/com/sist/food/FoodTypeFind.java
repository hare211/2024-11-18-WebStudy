package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sist.dao.*;
import com.sist.vo.FoodVO;

/**
 * Servlet implementation class FoodTypeFind
 */
@WebServlet("/FoodTypeFind")
public class FoodTypeFind extends HttpServlet {
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
		
		String type = request.getParameter("type");
		// Default 값 잡기(처음 접속했을 때)
		if (type == null) {
			type = "한식";
		}
		
		FoodDAO dao = FoodDAO.newInstance();
		List<FoodVO> list = dao.foodTypeFind(type, curPage);
		int totalPage = dao.foodTypeTotalPage(type);
		
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
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=\"row text-center\">");
		out.println("<a href=FoodTypeFind?type=한식 class=\"btn btn-sm btn-danger\">한식</a>");
		out.println("<a href=FoodTypeFind?type=양식 class=\"btn btn-sm btn-success\">양식</a>");
		out.println("<a href=FoodTypeFind?type=중식 class=\"btn btn-sm btn-info\">중식</a>");
		out.println("<a href=FoodTypeFind?type=일식 class=\"btn btn-sm btn-primary\">일식</a>");
		out.println("<a href=FoodTypeFind?type=카페 class=\"btn btn-sm btn-warning\">카페</a>");
		out.println("<a href=FoodTypeFind?type=기타 class=\"btn btn-sm btn-default\">기타</a>");
		
		
		out.println("</div>");
		out.println("<div class=row style=\"margin-top: 20px\">");
		
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
			out.println("<li><a href=\"FoodTypeFind?type=" + type + "&page=" + (startPage - 1) + "\">&lt;</a></li>");
		}
		/*
		 * http://localhost:8080/HTMLFoodProject/FoodTypeFind?type=%EC%96%91%EC%8B%9D
		 * GET 일 때만 자동으로 decoding
		 * 
		 */
		for (int i = startPage; i <= endPage; i++) {
			if (i == curPage) {
				out.println("<li class=active><a href=\"FoodTypeFind?type=" + type + "&page=" + i + "\">" + i + "</a></li>");
			} else {
				out.println("<li><a href=\"FoodTypeFind?type=" + type + "&page=" + i + "\">" + i + "</a></li>");
			}
		}
		if (endPage < totalPage) {
			out.println("<li><a href=\"FoodTypeFind?type=" + type + "&page=" + (endPage + 1) + "\">&gt;</a></li>");
		}
		
		out.println("</ul>");
		
		
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}


}
