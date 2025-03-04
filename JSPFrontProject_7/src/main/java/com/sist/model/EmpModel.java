package com.sist.model;

import java.io.PrintWriter;
import java.rmi.server.RMIClassLoader;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.*;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class EmpModel {
	@RequestMapping("emp/list.do")
	public String emp_list(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		return "../emp/list.jsp";
	}
	@RequestMapping("emp/list_ajax.do")
	public String emp_list_ajax(HttpServletRequest request, HttpServletResponse response) {
		
		List<EmpVO> list = EmpDAO.empListData();
		request.setAttribute("list", list);
		
		return "../emp/list_ajax.jsp";
	}
	@RequestMapping("emp/list_json.do")
	public void emp_list_json(HttpServletRequest request, HttpServletResponse response) {
		List<EmpVO> list = EmpDAO.empListData();
		try {
			// 자바에서의 데이터를 자바스크립트에 맞는 데이터로 변형
			JSONArray arr = new JSONArray();
			for (EmpVO vo : list) {
				JSONObject obj = new JSONObject();
				// {}
				obj.put("empno", vo.getEmpno());
				obj.put("ename", vo.getEname());
				obj.put("job", vo.getJob());
				obj.put("hiredate", vo.getDbday());
				obj.put("sal", vo.getSal());
				arr.add(obj);
			}
			// 전송
			response.setContentType("text/plain;charset=UTF-8"); // JSON 방식
			PrintWriter out = response.getWriter(); // out -> 요청한 브라우저
			out.write(arr.toJSONString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	// HTML 을 출력 시 String, JSON 을 출력 시 void
	@RequestMapping("food/list.do")
	public String food_list(HttpServletRequest request, HttpServletResponse response) {
		return "../food/list.jsp";
	}
	@RequestMapping("food/list_json.do")
	public void food_list_ajax(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		List<FoodVO> list = EmpDAO.foodTypeListData(type);
		try {
			JSONArray arr = new JSONArray();
			for (FoodVO vo : list) {
				JSONObject obj = new JSONObject();
				obj.put("fno", vo.getFno());
				obj.put("name", vo.getName());
				obj.put("poster", "https://www.menupan.com" + vo.getPoster());
				arr.add(obj);
			}
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(arr.toJSONString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
