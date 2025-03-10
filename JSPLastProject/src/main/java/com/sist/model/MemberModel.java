package com.sist.model;

import java.io.PrintWriter;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberModel {
	@RequestMapping("member/join.do")
	public String member_join(HttpServletRequest request, HttpServletResponse response) {
		
		
		request.setAttribute("main_jsp", "../member/join.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("member/idcheck.do")
	public String member_idcheck(HttpServletRequest request, HttpServletResponse response) {
		
		
		return "../member/idcheck.jsp";
	}
	@RequestMapping("member/idcheck_ok.do")
	public void memebr_idcheck_ok(HttpServletRequest request, HttpServletResponse response) {
		// data: {'id': id.trim()} 'id' 동일해야함
		String id = request.getParameter("id");
		int count = MemberDAO.memberIdcheck(id);
		
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(String.valueOf(count)); // int 를 char 로 변환해버림
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@RequestMapping("member/join_ok.do")
	public String member_join_ok(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String post = request.getParameter("post");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String content = request.getParameter("content");
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setSex(sex);
		vo.setBirthday(birthday);
		vo.setEmail(email);
		vo.setPost(post);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		vo.setPhone(phone1 + "-" + phone2);
		vo.setContent(content);
		
		MemberDAO.memberInsert(vo);
		return "redirect:../main/main.do";
	}
	@RequestMapping("member/login.do")
	public String member_login(HttpServletRequest request, HttpServletResponse response) {
		
		return "../member/login.jsp";
	}
	// 로그인
	@RequestMapping("member/login_ok.do")
	public void member_login_ok(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		MemberVO vo = MemberDAO.memberLogin(id, pwd);
		if (vo.getMsg().equals("OK")) {
			HttpSession session = request.getSession();
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			session.setAttribute("sex", vo.getSex());
			session.setAttribute("admin", vo.getAdmin());
		}
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(vo.getMsg());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@RequestMapping("member/logout.do")
	public String member_logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate(); // session 에 있는 모든 데이터를 지운다 -> 로그아웃
		return "redirect:../main/main.do";
	}
}
