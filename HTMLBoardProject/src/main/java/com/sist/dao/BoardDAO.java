package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.vo.BoardVO;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static BoardDAO dao;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	public BoardDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static BoardDAO newInstance() {
		if (dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void disconnection() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// 1. 목록
	public List<BoardVO> boardListData(int page) {
		List<BoardVO> list = new ArrayList<BoardVO>();

		try {
			getConnection();
			String sql = "SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD'), hit, num "
					   + "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
					         + "FROM (SELECT no, subject, name, regdate, hit "
					               + "FROM htmlboard "
					               + "ORDER BY no DESC)) "
					   + "WHERE num "
					   + "BETWEEN ? AND ?";
			
			ps = conn.prepareStatement(sql);
			
			int rowSize = 10;
			int start = (rowSize * page) - (rowSize - 1);
			int end = rowSize * page;
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				
				list.add(vo);
			}
			
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		
		return list;
	}
	
	// 1-1. 총페이지
	public int boardTotalPage() {
		int total = 0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*) / 10.0) "
					   + "FROM htmlboard";
			
			ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			total = rs.getInt(1);
			
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		return total;
	}
	
	// 상세보기
	public BoardVO boardDetail(int no) {
		BoardVO vo = new BoardVO();
		
		try {
			getConnection();
			String sql = "UPDATE htmlboard "
					   + "SET hit = hit + 1 "
					   + "WHERE no = " + no; // 조회수 증가
			
			ps = conn.prepareStatement(sql);
			
			ps.executeUpdate();
			
			sql = "SELECT no, name, subject, content, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS'), hit "
					   + "FROM htmlboard "
					   + "WHERE no = " + no;
			
			ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setDbday(rs.getString(5));
			vo.setHit(rs.getInt(6));
			
			rs.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		return vo;
	}
	// 글쓰기
	public void boardInsert(BoardVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO htmlboard(no, name, subject, content, pwd) VALUES(hb_no_seq.nextval, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			// 실행
			ps.executeUpdate(); // commit 포함
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
	}
	// 수정
	// 삭제
	public boolean boardDelete(int no, String pwd) {
		boolean bCheck = false;
		
		try {
			getConnection();
			String sql = "SELECT pwd "
					   + "FROM htmlboard "
					   + "WHERE no = " + no;
			
			ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			String db_pwd = rs.getString(1);
			
			rs.close();
			
			if (pwd.equals(db_pwd)) {
				bCheck = true;
				sql = "DELETE FROM htmlboard "
					+ "WHERE no = " + no;
				
				ps = conn.prepareStatement(sql);
				
				ps.executeUpdate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		return bCheck;
	}
}
