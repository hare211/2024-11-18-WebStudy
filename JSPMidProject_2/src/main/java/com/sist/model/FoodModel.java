package com.sist.model;
import java.util.*;
import com.sist.dao.*;
import jakarta.servlet.http.HttpServletRequest;
import com.sist.vo.*;

/*
 	단일 분할 할당 기법
 		오버레이 기법
 		스와핑 기법
 	다중 분할 할당 기법
 		고정 분할 할당 기법(정적)
 		가변 분할 할당 기법(동적)
 */
public class FoodModel {
	public void foodListData(HttpServletRequest request) {
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		int curPage = Integer.parseInt(page);
		
		Map map = new HashMap();
		
		int start = (12 * curPage) - 11;
		int end = 12 * curPage;
		
		map.put("start", start);
		map.put("end", end);
		
		// JSP 로 보내는 값들
		List<FoodVO> list = FoodDAO.foodListData(map);
		int totalPage = FoodDAO.foodTotalPage();
		
		// 블록별 페이지
		final int BLOCK = 10;
		/*
		 * 페이지수가 변경될 때 마다 페이지를 유지 또는 변경해야 함
		 */
		int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
		
	//	if (endPage > totalPage) {
	//		endPage = totalPage;
	//	}
		
		//request.setAttribute("list", list);
		request.setAttribute("curPage", curPage);
	//	request.setAttribute("totalPage", totalPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
	}
	
	public void foodDetailData(HttpServletRequest request) {
		String fno = request.getParameter("fno");
		//FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(fno));
		
		//request.setAttribute("vo", vo);
	}
}
