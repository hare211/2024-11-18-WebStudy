package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

public class ListModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		int curPage = Integer.parseInt(page);
		
		Map map = new HashMap();
		
		int start = (10 * curPage) - 9;
		int end = 10 * curPage;
		map.put("start", start);
		map.put("end", end);
		
		// 게시글 목록
		List<BoardVO> list = BoardDAO.boardListData(map);
		// 총페이지
		int totalPage = BoardDAO.boardTotalPage();
				
		request.setAttribute("list", list);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		
		return "board/list.jsp"; // request 를 받는 JSP
	}

}
