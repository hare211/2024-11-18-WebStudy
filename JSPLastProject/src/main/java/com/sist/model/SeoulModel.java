package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SeoulModel {
	private String[] tab = {"", "seoul_location", "seoul_nature", "seoul_shop", "seoul_food"};

	@RequestMapping("seoul/seoul_list.do")
	public String seoul_list(HttpServletRequest request, HttpServletResponse response) {
		
		String mode = request.getParameter("mode");
		String page = request.getParameter("page");
		
		if (page == null) {
			page = "1";
		}
		int curPage = Integer.parseInt(page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		int start = (curPage * 12) - 11;
		int end = curPage * 12;
		
		map.put("start", start);
		map.put("end", end);
		map.put("table_name", tab[Integer.parseInt(mode)]);
		List<SeoulVO> list = SeoulDAO.seoulListData(map);
		
		int totalPage = SeoulDAO.seoulTotalPage(map);
		
		final int BLOCK = 10;
		int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
		
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		
		request.setAttribute("list", list);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("mode", mode);
		
		request.setAttribute("main_jsp", "../seoul/seoul_list.jsp");
		
		return "../main/main.jsp";
	}
}
