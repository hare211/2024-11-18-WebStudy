package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sist.model.*;

/*
 	1. MVC 동작 구조
 	<a href="list.do">
 	<form action="insert.do">
 	
 	Model : 사용자 요청을 처리해 주는 클래스 집합
 			=> VO / DAO / Service / Manager => Model
 			Controller 로부터 받은 요청을 처리 => request 에 담는다
 			담은 request 를 보낼 Controller 지정
	View : JSP 로 제작 => request 를 받아서 출력
										--- JSTL / EL
		   사용자 요청
		   => <a> <form> ajax, location.href=""
    Controller : 사용자 요청을 받는 곳
    	   처리하는 Model 찾기
    	   Model 에서 담아준 request / session 을 JSP 로 전송
    	   Framework => Controller 가 제작되어 있다
    	   				---------> Spring / Struts
 */

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 클래스 저장
	private Map clsMap = new HashMap(); 
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try { // C:\webDev\webStudy\JSPMidProject_4\src\main\webapp\WEB-INF\application.xml
			String xml_path = "C:\\webDev\\webStudy\\JSPMidProject_4\\src\\main\\webapp\\WEB-INF\\application.xml";
			// xml 파싱
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document doc = db.parse(new File(xml_path)); 
			// root
			Element root = doc.getDocumentElement();
			
			//System.out.println(root.getTagName());
			NodeList list = root.getElementsByTagName("bean");
			for (int i = 0; i < list.getLength(); i++) {
				Element bean = (Element)list.item(i);
				String id = bean.getAttribute("id");
				String cls = bean.getAttribute("class");
				
				System.out.println(id + ": " + cls);
				
				Class clsName = Class.forName(cls);
				
				Object obj = clsName.getDeclaredConstructor().newInstance();
				
				System.out.println(id + ":" + obj);
				
				clsMap.put(id, obj);
			}
			// DOM Parsing : 데이터를 메모리에 트리 형태로 저장 관리
			// 실제 데이터만 추출 => SAX => 스프링에서 주로 사용
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// http://localhost/JSPMidProject_4/list.do
		// http://localhost/JSPMidProject_4/list.do?page=1
		// ? 앞까지만 URI
		// 사용자 요청 받기
		String uri = request.getRequestURI();
		System.out.println("전체 URI : " + uri); // -> /JSPMidProject_4/*.do
		
		
		// request.getContextPath() -> /JSPMidProject_4
		// request.getContextPath().length + 1 -> /JSPMidProject_4/  / 까지 자른다
		uri = uri.substring(request.getContextPath().length() + 1);
		
		System.out.println("clsMap : " + clsMap.keySet());
		System.out.println("요청된 URI: " + uri);

		Model model = (Model)clsMap.get(uri);
		
		String jsp = model.handlerRequest(request);
		
		/*
		 	if(jsp == null) {
		 		JSON 전송 => 자바스크립트에서 처리
	 		}
		 */
		if (jsp.startsWith("redirect:")) {
			jsp = jsp.substring(jsp.indexOf(":") + 1);
			response.sendRedirect(jsp);
			// _ok.do => 화면이동 (list or detail)
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
	}

}
