package com.sist.model;
// 사용자 요청 시 처리하는 클래스

import java.util.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;

import com.sist.dao.*;

public class GoodsModel {
	public void goodsListData(HttpServletRequest request) {
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		int curPage = Integer.parseInt(page);
		
		Map map = new HashMap();
		
		int rowSize = 12;
		
		int start = (rowSize * curPage) - (rowSize - 1);
		int end = rowSize * curPage;
		
		map.put("start", start);
		map.put("end", end);
		
		// JSP 로 보내는 값들
		List<GoodsVO> list = GoodsDAO.goodsListData(map);
		int totalPage = GoodsDAO.goodsTotalPage();
		
		request.setAttribute("list", list);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("curPage", curPage);
	}
}
