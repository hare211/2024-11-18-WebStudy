package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FoodBeforeDetail")
public class FoodBeforeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fno = request.getParameter("fno");
		
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("food_" + fno)) {
					cookies[i].setPath("/");
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}
			}
		}
		Cookie cookie = new Cookie("food_" + fno, fno);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24);
		
		response.addCookie(cookie);
		// 브라우저에 쿠키 저장
		// 보안에 취약 / 문자열만 저장 가능
		response.sendRedirect("FoodDetail?fno=" + fno); // GET
		
	}

}
