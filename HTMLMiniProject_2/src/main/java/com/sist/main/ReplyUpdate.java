package com.sist.main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sist.dao.ReplyDAO;

@WebServlet("/ReplyUpdate")
public class ReplyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 	tomcat => 9 버전
	 		javax.servlet
 		=> request.setCharacterEncoding("UTF-8")
 		=> Spring Tool => STS(9 버전까지만 사용 가능)
 		=> JDK 14
 		
 		tomcat => 10 버전 이산
 			jakarta.servlet
		
		STS 4 => tomcat 내장 => 임베디드 톰캣
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mno = request.getParameter("mno");
		String rno = request.getParameter("rno");
		String msg = request.getParameter("msg");
		
		ReplyDAO dao = ReplyDAO.newInstance();
		dao.replyUpdate(Integer.parseInt(rno), msg);
		response.sendRedirect("MainServlet?mode=2&mno=" + mno);
	}

}
