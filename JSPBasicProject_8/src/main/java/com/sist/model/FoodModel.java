package com.sist.model;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;

public class FoodModel {
	public void foodListData(HttpServletRequest request) {
		FoodDAO dao = new FoodDAO();
		
		String strPage = request.getParameter("page");
		if (strPage == null) {
			strPage = "1";
		}
		int curPage = Integer.parseInt(strPage);
		List<FoodVO> list = dao.foodListData(curPage);
		int totalPage = dao.getTotalPage();
		
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
		request.setAttribute("main_jsp", "home.jsp");
		
	}
}
