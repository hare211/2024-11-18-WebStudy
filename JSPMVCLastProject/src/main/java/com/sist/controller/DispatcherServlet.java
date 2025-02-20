package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sist.ann.*;
import java.net.*;
import java.io.*;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> clsList = new ArrayList<String>(); // 클래스 문자열로 저장
	
	// init -> 클래스만 가져온다(XML)
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try { //					  클래스 로드		   현재 위치 에서
			URL url = this.getClass().getClassLoader().getResource(".");
			// WEB-INF/classes -> root 폴더 (. 찍을 시)
			
			// 파일로 변경
			File file = new File(url.toURI());
			System.out.println("file : " + file);
			System.out.println("file.getPath() realPath -> " + file.getPath()); // realPath
			
			String path = file.getPath();
			path = path.replace("\\", File.separator);
			System.out.println("path : " + path);
			
			path = path.substring(0, path.lastIndexOf(File.separator));
			// WEB-INF/classes
			
			path = path + File.separator + "application.xml";
			
			// 패키지 명칭
			// XML 파싱기 생성
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// XML 파싱기
			DocumentBuilder db = dbf.newDocumentBuilder();
			// XML 파싱 시작 -> 태그나 속성에 있는 데이터 추출
			Document doc = db.parse(new File(path));
			// 최상위 태그 -> 데이터베이스(테이블 역할)
			Element beans = doc.getDocumentElement();
			// 같은 이름의 태그를 묶어서 관리
			NodeList list = beans.getElementsByTagName("context:component-scan");
			
			String pack = "";
			for (int i = 0; i < list.getLength(); i++) {
				Element elem = (Element)list.item(i);
				
				pack = elem.getAttribute("basePackage");
			}
			System.out.println("pack : " + pack);
			
			clsList = com.sist.ann.FileReader.componentScan(file.getPath(), pack);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	// 사용자(Client)가 요청 시 Model 과 연결 -> 결과값을 JSP 에 전송
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1. 사용자 요청 정보 받기
		// /JSPMVCLastProject/main/main.do -> request.getRequestURI()
		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length() + 1); // /JSPMVCLastProject -> getContextPath().length()
		System.out.println("URI substring :" + uri); // /JSPMVCLastProject/ -> getContextPath().length() + 1
		
		try {
			for (String cls : clsList) {
				// 클래스의 모든 정보 읽기
				Class<?> clsName = Class.forName(cls); 
				if (clsName.isAnnotationPresent(Controller.class) == false) { // 조회하는 클래스에 Controller Ann 이 없다면
					continue;
				}
				// @Controller 존재 시 메모리 할당
				Object obj = clsName.getDeclaredConstructor().newInstance();
				// 해당 메서드를 찾기 시작
				Method[] methods = clsName.getDeclaredMethods(); // 이 클래스 안에 선언된 모든 메서드를 가져온다
				
				for (Method m : methods) {
					RequestMapping rm = m.getAnnotation(RequestMapping.class);
					if (rm.value().equals(uri)) {
						String jsp = (String)m.invoke(obj, request);
						
						if (jsp == null) { // 반환값이 없을 때
							return; // ajax
						} else if (jsp.startsWith("redirect:")) {
							// sendRedirect
							jsp = jsp.substring(jsp.indexOf(":") + 1);
							response.sendRedirect(jsp);
						} else {
							// forward -> request 를 jsp 로 전송
							RequestDispatcher rd = request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						return;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
 