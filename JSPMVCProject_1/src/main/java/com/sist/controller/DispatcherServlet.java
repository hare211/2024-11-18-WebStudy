package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.*; // xml 파싱
import org.w3c.dom.*; // DOM, SAX 파싱

import com.sist.model.Model;

// DOM : XML 을 트리형태로 저장 후 데이터 읽기
// SAX : 태그를 한 개씩 읽어서 데이터 추출
// Controller => Model
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> clsMap = new HashMap<String, Object>();
	// XML 읽어서 클래스 확인
	public void init(ServletConfig config) throws ServletException {
		try {
			// 경로 호환을 위한 코드
			URL url = this.getClass().getClassLoader().getResource(".");
			File file = new File(url.toURI());
			System.out.println(file.getPath());
			//C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMVCProject_1\WEB-INF\classes
			
			String path = file.getPath();
			path = path.replace("\\", File.separator);
			// OS 에 따른 // 를 호환되는 것으로 변환(// => 윈도우 \\, 리눅스 //)
			path = path.substring(0, path.lastIndexOf(File.separator));
			System.out.println("substring 이후 path : " + path);
			//C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMVCProject_1\WEB-INF
			
			path = path + File.separator + "application.xml";
			System.out.println("File.separator, application.xml 더한 후 path : " + path);
			//C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMVCProject_1\WEB-INF\apllication.xml
			// path -> apllication.xml 을 찾기 위한 호환성
			
			// 1. XML 파서기 생성
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// 2. 파서기
			DocumentBuilder db = dbf.newDocumentBuilder();
			// 3. XML 을 읽어서 트리 형태로 저장
			Document doc = db.parse(new File(path));
			// 4. root 태그 읽기
			Element beans = doc.getDocumentElement();
			System.out.println("beans.getTagname : " + beans.getTagName());
			
			// 5. 같은 이름의 태그를 모아서 처리
			NodeList list = beans.getElementsByTagName("bean");
			
			// 6. bean 에 있는 id, class 의 값 추출
			for (int i = 0; i < list.getLength(); i++) {
				Element bean = (Element)list.item(i);
				String id = bean.getAttribute("id");
				String cls = bean.getAttribute("class");
				System.out.println("beans.getAttribute : " + id + " : " + cls);
				
				// 클래스 정보 읽기 => 메모리 할당 / 메서드 호출 / 멤버변수 주입
				// 클래스 정보를 읽기 위해서는 반드시 패키지.클래스명 형식으로
				
				Class clsName = Class.forName(cls);
				
				// 메모리 할당
				Object obj = clsName.getDeclaredConstructor().newInstance();
				// clsName.invoke()
				//Method[] method = clsName.getDeclaredMethods();
				//System.out.println(method[0].getParameters().length);
				
				//method[0].invoke(obj, null);
				
				clsMap.put(id, obj);
				System.out.println(id + " : " + obj);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("clsMapKey : " + clsMap.keySet());
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 사용자 요청 받기
		String uri = request.getRequestURI();
		System.out.println("기존 URI : " + uri);
		uri = uri.substring(uri.lastIndexOf("/") + 1);
		System.out.println("substring 이후 URI : " + uri);
		
		System.out.println("clsMap : " + clsMap.keySet());
		System.out.println("요청된 URI: " + uri);
		// Mdeol 클래스를 찾는다
		Model model = (Model)clsMap.get(uri);
		
		// model 이 null 일 경우 체크
		if (model == null) {
	        throw new ServletException("해당 URI에 대한 Model이 존재하지 않습니다: " + uri);
	    }
		// 처리된 결과를 어떤 jsp 에 전송할 지 확인
		String jsp = model.handlerRequest(request);
		System.out.println("jsp : " + jsp);
		// 이동 방식
		// sendRedirect / forward
		if (jsp.startsWith("redirect:")) {
			// 이미 만들어진 jsp 로 이동
			jsp = jsp.substring(jsp.indexOf(":") + 1);
			// return "redirect:list.do"
			response.sendRedirect(jsp);
		} else {
			// request 에 담긴 값 출력
			RequestDispatcher rd = request.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
		System.out.println("======================================");
		
	}

}
