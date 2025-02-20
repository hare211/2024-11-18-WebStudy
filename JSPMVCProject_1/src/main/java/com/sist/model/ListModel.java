package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
import java.text.*;

public class ListModel implements Model{
	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// 1. 사용자 요청 => page
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		
		// 현재 페이지 지정
		int curPage = Integer.parseInt(page);
		
		// 데이터 읽기
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		int start = (10 * curPage) - 9;
		int end = 10 * curPage;
		
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list = BoardDAO.boardListData(map);
		int total = BoardDAO.boardTotalPage();
		
		// list.jsp 로 값 전송
		request.setAttribute("list", list);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", total);
		
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		request.setAttribute("today", today);
		
		return "board/list.jsp";
	}
}
