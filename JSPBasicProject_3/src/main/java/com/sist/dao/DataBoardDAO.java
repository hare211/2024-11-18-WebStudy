package com.sist.dao;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataBoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static DataBoardDAO dao;
	
	public DataBoardDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static DataBoardDAO newInstance() {
		if (dao == null) {
			dao = new DataBoardDAO();
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
	// 목록 + 총페이지
	public List<DataBoardVO> dataBoardListData(int page) {
		List<DataBoardVO> list = new ArrayList<DataBoardVO>();
		
		try {
			getConnection();
			String sql = "SELECT no, subject, name, regdate, hit, num "
					   + "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
					         + "FROM (SELECT no, subject, name, regdate, hit "
					               + "FROM jspDataBoard ORDER BY no DESC)) "
					   + "WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, (page * 10) - 9);
			ps.setInt(2, (page * 10));
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				DataBoardVO vo = new DataBoardVO();
				
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
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
	public int dataBoardTotalPage() {
		int total = 0;
		
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*) / 10.0) FROM jspdataboard";
			
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
	// 상세보기 => 다운로드
	public DataBoardVO dataBoardDetail(int no) {
		DataBoardVO vo = new DataBoardVO();
		
		try {
			getConnection();
			String sql = "UPDATE jspdataboard "
					   + "SET hit = hit + 1 "
					   + "WHERE no = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, no);
			
			ps.executeUpdate();
			
			ps.close();
			
			sql = "SELECT no, name, subject, content, regdate, hit, filename, filesize "
			    + "FROM jspdataboard "
			    + "WHERE no = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, no);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			vo.setFilename(rs.getString(7));
			vo.setFilesize(rs.getInt(8));
			
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		
		return vo;
	}
	// 추가 => 업로드
	public void dataBoardInsert(DataBoardVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO jspdataboard VALUES(jdb_no_seq.nextval, ?, ?, ?, ?, SYSDATE, 0, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setString(5, vo.getFilename());
			ps.setInt(6, vo.getFilesize());
			
			ps.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
	}
	// 수정
	// 삭제 => 댓글 먼저 삭제 => 게시물 삭제
	public void dataBoardDelete(int no) {
		try {
			getConnection();
			String sql = "DELETE FROM jspdataboard WHERE no = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, no);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
	}
}
