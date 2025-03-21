package com.sist.main;

import java.util.*;
import java.sql.*;

public class ReserveDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static ReserveDAO dao;
	
	public ReserveDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
	public static ReserveDAO newInstance() {
		if (dao == null) {
			dao = new ReserveDAO();
		}
		return dao;
	}
	public List<Integer> foodGetFno() {
		List<Integer> list = new ArrayList<Integer>();
		try {
			getConnection();
			String sql = "SELECT fno FROM project_food ORDER BY fno ASC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int fno = rs.getInt(1);
				list.add(fno);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		return list;
	}
	public void foodRdaysUpdate(int fno, String rday) {
		try {
			getConnection();
			String sql = "UPDATE project_food SET rdays = ? WHERE fno = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, rday);
			ps.setInt(2, fno);
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
	}
	public void dayTimeInsert(int rno, int day, String time) {
		try {
			getConnection();
			String sql = "INSERT INTO reserve_day VALUES(?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rno);
			ps.setInt(2, day);
			ps.setString(3, time);
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
	}
}
