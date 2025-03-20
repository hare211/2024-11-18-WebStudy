package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.io.PrintWriter;
import java.util.*;

import org.json.simple.JSONObject;

import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.sist.dao.*;

@Controller
public class CartModel {
	@RequestMapping("cart/cart_insert.do")
	public String cart_insert(HttpServletRequest request, HttpServletResponse response) {
		String gno = request.getParameter("gno");
		String account = request.getParameter("account");
		String price = request.getParameter("price");
		
		HttpSession session = request.getSession(); // 세션에서 id 가져오기
		String id = (String)session.getAttribute("id");
		
		CartVO vo = new CartVO();
		vo.setAccount(Integer.parseInt(account));
		vo.setGno(Integer.parseInt(gno));
		vo.setPrice(Integer.parseInt(price));
		vo.setId(id);
		CartDAO.cartInsert(vo);
		
		return "redirect:../mypage/mypage_cart_list.do";
	}
	@RequestMapping("cart/cart_cancle.do")
	public String cart_cancle(HttpServletRequest request, HttpServletResponse response) {
		String cno = request.getParameter("cno");
		CartDAO.cartCancel(Integer.parseInt(cno));
		return "redirect:../mypage/mypage_cart_list.do";
	}
	@RequestMapping("cart/buy_insert.do")
	public void buy_insert(HttpServletRequest request, HttpServletResponse response) {
		String gno = request.getParameter("gno");
		String account = request.getParameter("account");
		String price = request.getParameter("price");
		
		HttpSession session = request.getSession(); // 세션에서 id 가져오기
		String id = (String)session.getAttribute("id");
		
		CartVO vo = new CartVO();
		vo.setAccount(Integer.parseInt(account));
		vo.setGno(Integer.parseInt(gno));
		vo.setPrice(Integer.parseInt(price));
		vo.setId(id);
		
		CartDAO.buyInsert(vo);
		MemberVO mvo = MemberDAO.memberInfoData(id);
		JSONObject obj = new JSONObject();
		obj.put("name", mvo.getName());
		obj.put("email", mvo.getEmail());
		obj.put("phone", mvo.getPhone());
		obj.put("address", mvo.getAddr1() + " " + mvo.getAddr2());
		obj.put("post", mvo.getPost());
		
		try {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toJSONString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
