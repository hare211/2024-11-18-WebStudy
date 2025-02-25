package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.io.PrintWriter;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FoodModel {
	@RequestMapping("food/food_find.do")
	public String food_find(HttpServletRequest request, HttpServletResponse response) {
		return "../food/food_find.jsp";
	}
	@RequestMapping("food/find_js.do")
	public void find_js(HttpServletRequest request, HttpServletResponse response) {
		// 오라클 데이터를 읽어서 JSON 으로 변경 -> 자바스크립트로 전송
		String page = request.getParameter("page");
		if (page == null) {
			page ="1";
		}
		String address= request.getParameter("fd");
		if (address == null) {
			address = "마포";
		}
		int curPage = Integer.parseInt(page);
		
		// List 받기
		Map<String, Object> map = new HashMap<String, Object>();
		
		int rowSize = 12;
		int start = (rowSize * curPage) - (rowSize - 1);
		int end = rowSize * curPage;
		
		map.put("start", start);
		map.put("end", end);
		map.put("address", address);
		
		List<FoodVO> list = FoodDAO.foodFindData(map);
		
		// 총페이지
		int totalPage = FoodDAO.foodFindTotalpage(address);
		// 블록
		final int BLOCK = 10;
		
		int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
		
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		// [{}, {}, {}...] List = JSONArray, VO = JSONObject
		JSONArray arr = new JSONArray();
		int i = 0;
		for (FoodVO vo : list) {
			JSONObject obj = new JSONObject();
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			obj.put("poster", "https://www.menupan.com" + vo.getPoster());
			if (i == 0) {
				obj.put("curPage", curPage);
				obj.put("totalPage", totalPage);
				obj.put("startPage", startPage);
				obj.put("endPage", endPage);
			}
			arr.add(obj);
			i++;
		}
		try {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			// 해당 브라우저를 찾아서 JSON 에 전송
			out.write(arr.toJSONString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	@RequestMapping("food/detail_js.do")
	public void food_detail(HttpServletRequest request, HttpServletResponse response) {
		String fno = request.getParameter("fno");
		FoodVO vo = FoodDAO.foodFindDataDetailData(Integer.parseInt(fno));
		
		JSONObject obj = new JSONObject();
		obj.put("poster", "https://www.menupan.com" + vo.getPoster());
		obj.put("name", vo.getName());
		obj.put("score", vo.getScore());
		obj.put("address", vo.getAddress());
		obj.put("phone", vo.getPhone());
		obj.put("type", vo.getType());
		obj.put("time", vo.getTime());
		obj.put("parking", vo.getParking());
		obj.put("theme", vo.getTheme());
		obj.put("content", vo.getContent());
		
		try {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			// 해당 브라우저를 찾아서 JSON 에 전송
			out.write(obj.toJSONString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
