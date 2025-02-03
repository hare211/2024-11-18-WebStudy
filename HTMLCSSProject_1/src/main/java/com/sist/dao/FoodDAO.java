package com.sist.dao;
import java.util.*;
import java.sql.*;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static FoodDAO dao;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	public FoodDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static FoodDAO newInstance() {
		if (dao == null) {
			dao = new FoodDAO();
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
	
	// 기능
	public List<FoodVO> foodListData() {
		List<FoodVO> list = new ArrayList<FoodVO>();
		
		try {
			getConnection();
			String sql = "SELECT fno, poster, name, type, address, rownum "
					   + "FROM (SELECT fno, poster, name, type, address "
					         + "FROM food_menupan "
					         + "ORDER BY fno ASC) "
					   + "WHERE rownum <= 50";
			
			ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				FoodVO vo = new FoodVO();
				
				vo.setFno(rs.getInt(1));
				vo.setPoster("https://www.menupan.com" + rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setType(rs.getString(4));
				vo.setAddress(rs.getString(5));
				
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
}
