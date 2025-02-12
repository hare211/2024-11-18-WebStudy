<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*" %>
<%
	String fn = request.getParameter("fn"); // 파일명
	try {
		// 다운로드 창 => Header
		File file = new File("c:\\upload", fn);
		response.setContentLength((int)file.length());
		response.setHeader("Content-Dispostion", "attachment;filename=" + URLEncoder.encode(fn, "UTF-8")); // 한글 포함
		
		// 파일 복사
		// 서버에 존재
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		
		// 복사할 대상 => 클라이언트 영역							=> 다운로드 폴더
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		byte[] buffer = new byte[1024];
		int i = 0; // 읽은 바이트 크기
		while((i = bis.read(buffer, 0, 1024)) != -1) {
			bos.write(buffer, 0, i);
		}
		bis.close();
		bos.close();
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	
%>    
