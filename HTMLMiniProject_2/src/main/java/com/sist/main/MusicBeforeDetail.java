package com.sist.main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class MusicBeforeData
 */
@WebServlet("/MusicBeforeDetail")
public class MusicBeforeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mno = request.getParameter("mno");
		
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("music_" + mno)) {
					cookies[i].setPath("/");
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}
			}
		}
		Cookie cookie = new Cookie("music_" + mno, mno);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24);
		
		response.addCookie(cookie);
		
		response.sendRedirect("MainServlet?mode=2&mno=" + mno);
	}

}
