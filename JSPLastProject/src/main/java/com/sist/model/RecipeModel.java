package com.sist.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.RecipeDAO;
import com.sist.vo.ChefVO;
import com.sist.vo.RecipeVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class RecipeModel {
	@RequestMapping("recipe/recipe_list.do")
	public String recipe_list(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		
		if (page == null) {
			page = "1";
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		int curPage = Integer.parseInt(page);
		int start = (12 * curPage) - 11;
		int end = 12 * curPage;
		
		map.put("start", start);
		map.put("end", end);
		
		List<RecipeVO> list = RecipeDAO.recipeListData(map);
		int totalPage = RecipeDAO.recipeChefTotalPage();
		
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
		
		request.setAttribute("main_jsp", "../recipe/recipe_list.jsp");
		
		return "../main/main.jsp"; // main 위에 recipe_list.jsp 를 덮어씌운다
	}
	@RequestMapping("recipe/chef_list.do")
	public String chef_list(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		
		if (page == null) {
			page = "1";
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		int curPage = Integer.parseInt(page);
		int start = (12 * curPage) - 11;
		int end = 12 * curPage;
		
		map.put("start", start);
		map.put("end", end);
		
		List<ChefVO> list = RecipeDAO.recipeChefListData(map);
		int totalPage = RecipeDAO.recipeChefTotalPage();
		
		request.setAttribute("list", list);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		
		request.setAttribute("main_jsp", "../recipe/chef_list.jsp");
		return "../main/main.jsp";
	}
	
}
