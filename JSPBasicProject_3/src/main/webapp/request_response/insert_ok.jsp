<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*, com.sist.dao.*" %>
    
<%

/*

	server => context.xml
	xml 파일은 지정된 속성만 사용 가능
		대소문자 구분
		지정된 태그 / 속성을 정의하고 있는 파일
		.dtd
		속성은 반드시 "" 을 사용한다
*/
	// 오라클에 데이터 첨부
	DataBoardVO vo = new DataBoardVO();
	DataBoardDAO dao = DataBoardDAO.newInstance();
	
	// 사용자가 보내준 데이터
	String name = request.getParameter("name");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	String pwd = request.getParameter("pwd");
	
	// 사용자가 데이터 전송 request.getParameter("name")
	// 사용자가 파일 전송 request.getPart("upload");
	
	Part filePart = request.getPart("upload");
	String fileName = filePart.getSubmittedFileName();
	
	if(fileName == null || fileName.equals("")) { // 업로드가 안된 상태
		vo.setFilename("");
		vo.setFilesize(0);
	} else { // 업로드 된 상태
		String uploadDir="c:\\upload";
		File file = new File(uploadDir, fileName);
		
		try (InputStream input = filePart.getInputStream();
			 FileOutputStream output = new FileOutputStream(file)){
			byte[] buffer = new byte[1024];
			int i = 0;
			while((i = input.read(buffer, 0, 1024)) != -1) {
				output.write(buffer, 0, i);
			}
		}
		
		vo.setFilename(fileName);
		vo.setFilesize((int)file.length());
	}
	
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	dao.dataBoardInsert(vo);
	
	System.out.println("name: " + name);
	System.out.println("subject: " + subject);
	System.out.println("content: " + content);
	System.out.println("pwd: " + pwd);
	System.out.println("fileName: " + fileName);
	
	
	response.sendRedirect("list.jsp");
	
%>    
