package com.sist.model;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BoardModel {
	@RequestMapping("board/board_list.do")
	public String board_list(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		
		int curPage = Integer.parseInt(page);
		Map map = new HashMap();
		
		int start = (10 * curPage) - 9;
		int end = (10 * curPage);
		
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list = BoardDAO.boardListData(map);
		int totalPage = BoardDAO.boardTotalPage();
		
		request.setAttribute("list", list);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("curPage", curPage);
		
		
		request.setAttribute("main_jsp", "../board/board_list.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("board/board_insert.do")
	public String board_insert(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp", "../board/board_insert.jsp");
		
		   return "../main/main.jsp";
	}
	@RequestMapping("board/board_insert_ok.do")
	public String board_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		BoardVO vo = new BoardVO();
		
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		BoardDAO.boardInsert(vo);
		return "redirect:../board/board_list.do";
	}
	// 뒤로가기 시 조회수가 증가해야 하는 상황이면 리스트로 돌아가는 방법 history 로 하면 안됨
	@RequestMapping("board/board_detail.do")
	public String board_detail(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String page = request.getParameter("page");
		
		
		BoardVO vo = BoardDAO.boardDetailData(Integer.parseInt(no));
		
		request.setAttribute("page", page);
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../board/board_detail.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("board/board_pwd_ajax.do")
	public void board_pwd_ajax(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String pwd = request.getParameter("pwd");
		String db_pwd = BoardDAO.boardGetPassword(Integer.parseInt(no));
		
		int res = 0;
		if (db_pwd.equals(pwd)) {
			res = 1;
		}
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(String.valueOf(res));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@RequestMapping("board/board_delete_ajax.do")
	public void board_delete_ajax(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		PrintWriter out = null;
		try {
			BoardDAO.boardDelete(Integer.parseInt(no));
			
			out = response.getWriter(); // 여기까지 무조건 수행되어야 함
			
			response.setContentType("text/html;charset=UTF-8");
			out.write("yes");
		} catch (Exception ex) {
			out.write("no");
			ex.printStackTrace();
		}
	}
}
