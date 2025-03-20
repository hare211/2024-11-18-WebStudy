package com.sist.model;

import java.util.List;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.CartDAO;
import com.sist.vo.CartVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyPageModel {
	@RequestMapping("mypage/my_main.do")
	public String my_main(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp", "../mypage/my_main.jsp");
		request.setAttribute("my_jsp", "../mypage/my_home.jsp");
		return "../main/main.jsp";
	}
	
	// ===
	@RequestMapping("mypage/mypage_cart_list.do")
	public String mypage_cart_list(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		List<CartVO> list = CartDAO.cartListData(id);
		request.setAttribute("list", list);
		request.setAttribute("my_jsp", "../cart/cart_list.jsp");
		request.setAttribute("main_jsp", "../mypage/my_main.jsp");
		return "../main/main.jsp";
	}
}
