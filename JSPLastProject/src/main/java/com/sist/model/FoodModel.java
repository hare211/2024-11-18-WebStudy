package com.sist.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// JSP(디자인) -> Model -> DAO -> Model -> JSP
@Controller
public class FoodModel {
	@RequestMapping("food/food_list.do")
	public String food_list(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		int curPage = Integer.parseInt(page);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		int start = (curPage * 12) - 11;
		int end = curPage * 12;
		
		map.put("start", start);
		map.put("end", end);
		
		List<FoodVO> list = FoodDAO.foodListData(map);
		
		int totalPage = FoodDAO.foodTotalPage();
		
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
		request.setAttribute("main_jsp", "../food/food_list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("food/food_detail_before.do")
	public String food_detail_before(HttpServletRequest request, HttpServletResponse response) {
		String fno = request.getParameter("fno");
		
		Cookie cookie = new Cookie("food_" + fno, fno);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24);
		
		// 전송
		response.addCookie(cookie);
		
		return "food_detail.do?fno=" + fno;
	}
	
	@RequestMapping("food/food_detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response) {
		String fno = request.getParameter("fno");
		FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(fno));
		String addr1 = vo.getAddress().trim();
		/*
		addr1 = addr1.trim().substring(addr1.indexOf(" "));
		String addr2 = addr1.trim().substring(addr1.indexOf(" "));
		String addr3 = addr2.trim().substring(0, addr2.indexOf(" "));
		*/
		/*
		 	String addr = vo.getAddress();
		 	addr = addr.substring(addr.trim().indexOf(" "));
		 	String addr1 = addr.trim();
		 	addr1 = addr1.substring(addr1.trim().indexOf(" "));
		 	String addr2 = addr1.trim();
		 	addr2 = addr2.substring(0, addr2.indexOf(" "));
		 */
		// 강원 -> s1 양양군 -> s2 양양읍 -> addr3 연창리 180-108
		StringTokenizer st = new StringTokenizer(addr1);
		String s1 = st.nextToken();
		String s2 = st.nextToken();
		String addr3 = st.nextToken();
		request.setAttribute("vo", vo);
		request.setAttribute("addr", addr3.trim());
		request.setAttribute("main_jsp", "../food/food_detail.jsp");
		
		return "../main/main.jsp";
	}
}
