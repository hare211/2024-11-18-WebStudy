package com.sist.main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;

import com.sist.dao.*;
import com.sist.vo.*;

/**
 * Servlet implementation class MusicDetail
 */
@WebServlet("/MusicDetail")
public class MusicDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// 브라우저로 전송
		PrintWriter out = response.getWriter();
		/*
		 * request
		 * => 클라이언트의 정보(ip, port)
		 * => 사용자 요청 정보(사용자가 보낸 값)
		 * 		= getParameter() : String 단일 값
		 * 		= getParameterValues() : String[] 다중 값(checkbox)
		 * 		= encoidng(byte[]) 전송 => decoding(원상 복귀)
		 * 			=> setCharacterEncoding("UTF-8");
		 * response
		 * => 서버에서 전송 정보(HTML, Cookie)
		 * 					----- ------- addCookie()
		 * 					setContentType()
		 * => 화면 이동 정보
		 */
		// 1. 사용자가 보낸 데이터 받기
		String mno = request.getParameter("mno");
		// 2. 데이터베이스 연결
		MusicDAO dao = MusicDAO.newInstance();
		MusicVO vo = dao.getMusicDetail(Integer.parseInt(mno));
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("<script src=js/update.js></script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=row>");
		
		out.println("<table class=table>");
		out.println("<tr>");
		/*
		StringTokenizer st = new StringTokenizer(vo.getImages(), ",");
		while (st.hasMoreTokens()) {
			out.println("<td class=text-center>");
			out.println("<img src=https://www.menupan.com" + st.nextToken() + " style=\"width:130px; height:100px\">");
			out.println("</td>");
		}
		*/
		out.println("</tr>");
		out.println("</table>");
		
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td width=30% class=text-center rowspan=8>");
		out.println("<img src=" + vo.getPoster() + " style=\"width:200px; height:200px\">");
		out.println("<p>" + vo.getIdcrement() + "&nbsp;&nbsp;" + vo.getState() + "</p>");
		out.println("</td>");
		out.println("<td colspan=2>");
		out.println("<h3>" + vo.getTitle() + "&nbsp;<span style=\"color:orange\">" + "</span></h3>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">가수</td>");
		out.println("<td width=60%>" + vo.getSinger() + "</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">앨범</td>");
		out.println("<td width=60%>" + vo.getAlbum() + "</td>");
		out.println("</tr>");
		/*
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">등락</td>");
		out.println("<span><td width=60%>" + vo.getIdcrement() + "&nbsp;&nbsp;" + vo.getState() + "</td></sapn>");
		out.println("</tr>");
		*/
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">장르</td>");
		out.println("<td width=60%>" + vo.getGenre() + "</td>");
		out.println("</tr>");
		/*
		out.println("<tr>");
		out.println("<td width=10% style=\"color=gray\">주차</td>");
		out.println("<td width=60%>" + vo.getParking() + "</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color=gray\">가격대</td>");
		out.println("<td width=60%>" + vo.getPrice() + "</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color=gray\">테마</td>");
		out.println("<td width=60%>" + vo.getTheme() + "</td>");
		out.println("</tr>");
		*/
		
		out.println("</table>");
		
		out.println("<table class=table>");
		/*
		out.println("<tr>");
		out.println("<td>");
		out.println(vo.getContent());
		out.println("</td>");
		out.println("</tr>");
		*/
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		out.println("<tr>");
		out.println("<td class=text-right>");
		if (id != null) {
			
		out.println("<a href=# class=\"btn btn-xs btn-danger\">좋아요</a>");
		out.println("<a href=# class=\"btn btn-xs btn-success\">찜하기</a>");
		out.println("<a href=# class=\"btn btn-xs btn-info\">예약</a>");
		}
		out.println("<a href=MainServlet class=\"btn btn-xs btn-primary\">목록</a>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
		// 지도
		// 댓글
		out.println("</div>");
		
		out.println("<div class=row style=\"margin-top: 20px\">");
		out.println("<div class=col-sm-8>");
		out.println("<h3>댓글</h3>");
		// 댓글 출력
		ReplyDAO rdao = ReplyDAO.newInstance();
		List<ReplyVO> list = rdao.replyListData(Integer.parseInt(mno));
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td>");
		// 댓글 부분
		for (ReplyVO rvo : list) {
			out.println("<table class=table>");
			out.println("<tr>");
			out.println("<td class=text-left>");
			
			out.println("□" + rvo.getName() + "&nbsp;(");
			out.println(rvo.getDbday() + ")");
			
			out.println("</td>");
			out.println("<td class=text-right>");
			
			if (rvo.getId().equals(id)) { // id -> session 에 저장된 id
				// session 에 저장된 id 와 db 에 저장된 id 가 같을 경우
				out.println("<span class=\"btn btn-xs btn-success update\" data-rno=" + rvo.getRno()+ ">수정</span>");
				out.println("<a href=ReplyInsert?mno=" + mno + "&rno=" + rvo.getRno() + " class=\"btn btn-xs btn-info\">삭제</a>");
			}								//	fno : 삭제한 상태로 이동, rno : 삭제할 댓글의 번호
											//  ReplyInsert 의 deGet 메서드에서 request 객체 
			
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td colspan=2>");
			out.println("<pre style=\"white-space:pre-wrap; background-color:white; border:none\"> " + rvo.getMsg() + "</pre>");
			
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr id=\"m" + rvo.getRno() + "\" class=ups style=\"display:none\">"); 
			out.println("<td>");
			
			out.println("<form method=post action=ReplyUpdate>");
			out.println("<textarea rows=3 cols=58 name=msg style=\"float:left; font-size: 13px;\" required>" + rvo.getMsg() + "</textarea>");
			out.println("<input type=hidden name=mno value=" + mno + ">"); // 번호 write
			out.println("<input type=hidden name=rno value=" + rvo.getRno() + ">"); // 번호 write
			out.println("<input class=\"btn btn-primary\" type=submit value=\"수정\" style=\"float:left; width:70px; height:62px;\">");
			out.println("</form>");
			
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
		}
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		if (id != null) { // 로그인 된 경우에만 사용
			out.println("<form method=post action=ReplyInsert>"); // ReplyInsert 로 fno write
			out.println("<table class=table>");
			out.println("<tr>");
			out.println("<td>");
			out.println("<textarea rows=3 cols=58 name=msg style=\"float:left\" required></textarea>");
			out.println("<input type=hidden name=mno value=" + mno + ">"); // 번호 write
			out.println("<input class=\"btn btn-primary\" type=submit value=댓글쓰기 style=\"float:left; width:80px; height:75px;\">");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
		}
		out.println("</div>");
		
		
		out.println("<div class=col-sm-4>");
		out.println("<h3>인기 곡</h3>");
		List<MusicVO> mList = dao.musicHitTop10();
		out.println("<table class=\"table table-striped\">");
		out.println("<tr>");
		out.println("<th class=text-center></th>");
		out.println("<th class=text-center>곡명</th>");
		out.println("<th class=text-center>조회수</th>");
		for (MusicVO mvo : mList) {
			out.println("<tr>");
			out.println("<td class=text-center><img src=" + mvo.getPoster() + " width=30 height=30></td>");
			out.println("<td>" + mvo.getTitle() + "</td>");
			out.println("<td class=text-center>" + mvo.getHit() + "</td>");
			out.println("</tr>");
			
		}
		out.println("</tr>");
		out.println("</table>");
		out.println("</div>");
		
		out.println("</div>");
		
		
		
		
		
		
		
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}


}
