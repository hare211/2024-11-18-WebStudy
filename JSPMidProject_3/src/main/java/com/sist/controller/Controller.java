package com.sist.controller;
import jakarta.servlet.RequestDispatcher;
/*
 	객체(Object) 모델링 : 정보 모델링이라고도 하며, 속성과 연산 식별 및 객체들 간의
 					   관계를 규정하여 객체 다이어그램으로 표시하는 것
    동적(Dynamic) 모델링 : 상태 다이어그램을 이용하여 객체들 간의 동적인 행위를 표현하는 모델링
    기능(Functional) 모델링 : 자료 흐름도를 이용하여 프로세스들 간의 처리과정을 표현한 모델링
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sist.model.DeleteModel;
import com.sist.model.InsertModel;
import com.sist.model.ListModel;
import com.sist.model.UpdateModel;

/*
 	Model : DB 처리, DAO, VO, 처리내용 전송
 	
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 사용자 요청 값 가져온다
		String cmd = request.getParameter("cmd");
		if (cmd == null) {
			cmd = "list";
		}
		
		String jsp = "";
		if (cmd.equals("list")) {
			ListModel model = new ListModel();
			model.execute(request);
			
			jsp = "list.jsp";
		} else if (cmd.equals("insert")) {
			InsertModel model = new InsertModel();
			model.execute(request);
			
			jsp = "insert.jsp";
		} else if (cmd.equals("update")) {
			UpdateModel model = new UpdateModel();
			model.execute(request);
			
			jsp = "update.jsp";
		} else if (cmd.equals("delete")) {
			DeleteModel model = new DeleteModel();
			model.execute(request);
			
			jsp = "delete.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher("board/" + jsp);
		rd.forward(request, response);
	}

}
