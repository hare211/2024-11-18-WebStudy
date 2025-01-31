package com.sist.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

@WebServlet("/BoardInsert")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// request : 사용자가 보내준 데이터
		// reponse : 사용자 브라우저 정보 => 화면 변경 역할
		// 2 byte 로 전송값을 받는다(디코딩)
		request.setCharacterEncoding("UTF-8");
		
		// request.getParameter() : 사용자가 요청한 값을 읽는 메서드
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		System.out.println(name);
		System.out.println(subject);
		System.out.println(content);
		System.out.println(pwd);
		
		// 오라클 연동
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		BoardDAO dao = BoardDAO.newInstance();
		dao.boardInsert(vo);
		// JSP 동일
		response.sendRedirect("BoardList"); // 데이터 insert 후 boardlist 로 이동
		// 흐름 숙지
		// 요청 후 어떤 파일로 이동해야하는 지
	}

}
