package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;

public class ReplyDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static ReplyDAO dao;
	
	public ReplyDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static ReplyDAO newInstance() {
		if (dao == null) {
			dao = new ReplyDAO();
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
	public void replyInsert(ReplyVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO music_reply VALUES(mreply_rno_seq.nextval, ?, ?, ?, ?, SYSDATE)";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, vo.getMno());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getMsg());
			
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		
	}
	public List<ReplyVO> replyListData(int mno){
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		try {
			getConnection();
			String sql = "SELECT rno, mno, id, name, msg, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') "
					   + "FROM music_reply "
					   + "WHERE mno = ?"
					   + "ORDER BY rno DESC";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, mno);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				ReplyVO vo = new ReplyVO();
				
				vo.setRno(rs.getInt(1));
				vo.setMno(rs.getInt(2));
				vo.setId(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setMsg(rs.getString(5));
				vo.setDbday(rs.getString(6));
				
				list.add(vo);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		return list;
	}
	public void replyDelete(int rno) {
		try {
			getConnection();
			String sql = "DELETE FROM music_reply WHERE rno = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, rno);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
	}
	public void replyUpdate(int rno, String msg) {
		try {
			getConnection();
			String sql = "UPDATE music_reply SET msg = ? WHERE rno = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, msg);
			ps.setInt(2, rno);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
	}
}
