package com.sist.model;

import com.sist.controller.*;
import com.sist.vo.*;
import com.sist.dao.*;
import java.util.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FoodModel {
	@RequestMapping("food/list.do")
	public String food_list(HttpServletRequest request, HttpServletResponse response) {
		
		String page = request.getParameter("page");
		
		if (page == null) {
			page = "1";
		}
		
		int curPage = Integer.parseInt(page);
		
		// 1. DB 연동
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		int start = (20 * curPage) - 19;
		int end = (20 * curPage);
		
		map.put("start", start);
		map.put("end", end);
		
		List<FoodVO> list = FoodDAO.foodListData(map);
		
		int totalPage = FoodDAO.foodTotalPage();
		
		final int BLOCK = 10;
		
		int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curPage -1) / BLOCK * BLOCK) + BLOCK;
		
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		// 2. 결과값 전송 -> request.setAttribute()
		request.setAttribute("list", list);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		// 3. include 파일 지정
		request.setAttribute("main_jsp", "../food/list.jsp");
		
		return "../main/main.jsp"; // main.jsp 로 이동하여 해당하는 jsp 를 실행하여 출력
	}
	@RequestMapping("food/detail_before.do")
	public String food_detail_before(HttpServletRequest request, HttpServletResponse response) {
		String fno = request.getParameter("fno");
		// 쿠키 저장
		// 1. Cookie 생성
		Cookie cookie = new Cookie("food_" + fno, fno);
		// 2. Path 설정
		cookie.setPath("/");
		// 3. 저장 기간
		cookie.setMaxAge(60 * 60 * 24);
		// 4. 브라우저로 전송
		response.addCookie(cookie);
		// 상세보기
		return "redirect:../food/detail.do?fno=" + fno;
	}
	@RequestMapping("food/detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response) {
		String fno = request.getParameter("fno");
		FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(fno));
		// 카카오 맵 주소 자르기
		String addr1 = vo.getAddress().trim();
		/*
		addr1 = addr1.trim().substring(addr1.indexOf(" "));
		String addr2 = addr1.trim().substring(addr1.indexOf(" "));
		String addr3 = addr2.trim().substring(0, addr2.indexOf(" "));
		*/
		
		// 강원 -> s1 양양군 -> s2 양양읍 -> addr3 연창리 180-108
		StringTokenizer st = new StringTokenizer(addr1);
		String s1 = st.nextToken();
		String s2 = st.nextToken();
		String addr3 = st.nextToken();
		
		System.out.println("addr3 :" + addr3.trim());
		
		request.setAttribute("vo", vo);
		request.setAttribute("addr", addr3.trim());
		
		request.setAttribute("main_jsp", "../food/detail.jsp");
		return "../main/main.jsp";
	}
}
