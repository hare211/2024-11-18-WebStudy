package com.sist.main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/ReplyInsert") // action=ReplyInsert 에서 ReplyInsert 를 찾는 과정에서 톰캣이 찾는 곳은 @WebServlet 이 부분 
public class ReplyInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mno = request.getParameter("mno");
		String rno = request.getParameter("rno");
		
		ReplyDAO dao = ReplyDAO.newInstance();
		// request 객체에서 받아온 rno 에 해당하는 댓글 삭제(삭제 버튼 a href=ReplyInsert?fno=" + fno + "&rno=" + rvo.getRno() << 여기서 request)
		dao.replyDelete(Integer.parseInt(rno));
		
		// 삭제 후 이동
		response.sendRedirect("MainServlet?mode=2&mno=" + mno);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mno = request.getParameter("mno");
		String msg = request.getParameter("msg");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		
		ReplyVO vo = new ReplyVO();
		vo.setMno(Integer.parseInt(mno));
		vo.setMsg(msg);
		vo.setId(id);
		vo.setName(name);
		
		ReplyDAO dao = ReplyDAO.newInstance();
		dao.replyInsert(vo);
		
		// 화면 출력(FoodDetail 로)
		response.sendRedirect("MainServlet?mode=2&mno=" + mno);
	}

}
