<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	TTP 프로토콜은 새로운 페이지가 실행되면 기존의 입력된 모든 정보는 사라진다
	---------> 로그인 시 새로운 페이지로 이동하면 로그인 정보는 사라진다(지속성 or 유지성 X)
	Cookie
		=> 상태의 지속성
		   --- 데이터의 변경 상황
		   React / Vue => state 관리 프로그램(데이터 상태프로그램)
	    => 데이터를 유지하기 위한 메모리 저장 공간
	       브라우저(클라이언트)
        => 사용법
        	1. 저장
        		new Cookie(String key, String value)
        		Map 을 사용한다
        		key 중복 불가
        		*** response
        			한 개 JSP 에서 한 가지 일만 수행
        				HTML or Cookie 하나만 전송
        	2. 읽기
        		Cookie[] cookies = request.getCookies()
        		@CookieValue => Spring
        			| Annotation
        	3. 삭제
        		setMaxAge(0)
        	4. 저장 기간 지정
        		setMaxAge() / 60 * 60 * 24 (초단위) -> 24시간 / default : 30분
        	5. 저장된 데이터 읽기
        		=> 키 읽기 : getName()
        		=> 값 읽기 : getValue()
       		6. 저장 경로 설정
       			setPath("/")
       		7. 저장할 수 있는 데이터 : String 만 가능
       		8. port 가 다르면 서버에서 Cookie 를 다룰 수 없다
       		
       		React -> 3000port
       		Spring-Boot -> 80, 8080port
       		=> js 쿠키를 이용한다
       		9. 자동 로그인 => 스프링(remember-me)
       		
 --%>    
<%
	// 전송 값을 받는다(?fno=)
	String fno = request.getParameter("fno");

	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for(int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("food_" + fno)) {
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
				// 브라우저로 전송
				break;
			}
		}
	}
	// 새로 쿠키 추가
	Cookie cookie = new Cookie("food_" + fno, fno);
	cookie.setPath("/");
	cookie.setMaxAge(60 * 60 * 24);
	response.addCookie(cookie);
	
	// 화면 이동
	response.sendRedirect("detail.jsp?fno=" + fno);
%>