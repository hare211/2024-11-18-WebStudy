package com.sist.model;

import java.util.List;

import com.sist.dao.EmpDAO;
import com.sist.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;

public class EmpModel {
	public void empListData(HttpServletRequest request) {
		EmpDAO dao = new EmpDAO();
		
		List<EmpVO> list = dao.empListData();
		
		request.setAttribute("list", list);
	}
}
