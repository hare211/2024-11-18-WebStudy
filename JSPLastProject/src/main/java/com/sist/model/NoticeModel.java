package com.sist.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class NoticeModel {
	private String[] types = {"", "일반공지", "이벤트공지", "맛집공지", "여행공지", "레시피공지"};
	@RequestMapping("admin/notice_list.do")
	public String notice_list(HttpServletRequest request, HttpServletResponse response) {
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
		
		List<NoticeVO> list = NoticeDAO.noticeListData(map);
		
		for (NoticeVO vo : list) {
			vo.setTypes(types[vo.getType()]);
		}
		
		int count = NoticeDAO.noticeTotalPage();
		int totalPage = (int)(Math.ceil(count / 10.0));
		count = count - ((curPage * 10) - 10);
		
		request.setAttribute("list", list);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("curPage", curPage);
		request.setAttribute("count", count);
		request.setAttribute("admin_jsp", "../notice/notice_list.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("admin/notice_insert.do")
	public String notice_insert(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("admin_jsp", "../notice/notice_insert.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("admin/notice_insert_ok.do")
	public String notice_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		NoticeVO vo = new NoticeVO();
		vo.setType(Integer.parseInt(type));
		vo.setSubject(subject);
		vo.setContent(content);
		NoticeDAO.noticeInsert(vo);
		return "redirect:../admin/notice_list.do";
	}
	@RequestMapping("admin/notice_list.do")
	public String notice_user_list(HttpServletRequest request, HttpServletResponse response) {
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
		
		List<NoticeVO> list = NoticeDAO.noticeListData(map);
		
		for (NoticeVO vo : list) {
			vo.setTypes(types[vo.getType()]);
		}
		
		int count = NoticeDAO.noticeTotalPage();
		int totalPage = (int)(Math.ceil(count / 10.0));
		count = count - ((curPage * 10) - 10);
		
		request.setAttribute("list", list);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("curPage", curPage);
		request.setAttribute("count", count);
		request.setAttribute("admin_jsp", "../notice/notice_user_list.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("admin/notice_detail.do")
	public String notice_detail(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		NoticeVO vo = NoticeDAO.noticeDetailData(Integer.parseInt(no), 0); // 조회수 증가 X
		
		vo.setTypes(types[vo.getType()]);
		
		request.setAttribute("admin_jsp", "../notice/notice_insert.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("notice/notice_user_detail.do")
	public String notice_user_detail(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		NoticeVO vo = NoticeDAO.noticeDetailData(Integer.parseInt(no), 1); // 조회수 증가 O
		
		vo.setTypes(types[vo.getType()]);
		
		request.setAttribute("main_jsp", "../notice/notice_user_detail.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("notice/notice_user_list.do")
	public String notice_user_list1(HttpServletRequest request, HttpServletResponse response) {
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
		
		return "";
	}
	
}
