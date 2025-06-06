package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static FoodDAO dao;
	
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
	// 목록
	public List<FoodVO> foodListData(int page) {
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			String sql = "SELECT fno, name, poster, num "
					   + "FROM (SELECT fno, name, poster, rownum as num "
					         + "FROM (SELECT /*+ INDEX_ASC(food_menupan fm_fno_pk) */ fno, name, poster "
					               + "FROM food_menupan)) "
					   + "WHERE num BETWEEN ? AND ?";
			
			ps = conn.prepareStatement(sql);
			
			int rowSize = 12;
			int start = (rowSize * page) - (rowSize - 1);
			int end = rowSize * page;
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				FoodVO vo = new FoodVO();
				
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster("https://www.menupan.com" + rs.getString(3));
				
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
	// 총페이지
	public int getTotalPage() {
		int totalPage = 0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*) / 12.0) "
					   + "FROM food_menupan";
			
			ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			totalPage = rs.getInt(1);
			
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		
		return totalPage;
	}
	// 상세보기
	public FoodVO getFoodDetail(int fno) {
		FoodVO vo = new FoodVO();
		try {
			getConnection();
			String sql = "UPDATE food_menupan SET hit = hit + 1 WHERE fno = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, fno);
			
			ps.executeUpdate();
			
			sql = "SELECT name, type, phone, address, theme, poster, images, time, parking, content, price, score, hit FROM food_menupan WHERE fno = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, fno);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			vo.setName(rs.getString("name"));
			vo.setType(rs.getString("type"));
			vo.setPhone(rs.getString("phone"));
			vo.setAddress(rs.getString("address"));
			vo.setTheme(rs.getString("theme"));
			vo.setPoster("https://www.menupan.com" + rs.getString("poster"));
			vo.setImages(rs.getString("images"));
			vo.setTime(rs.getString("time"));
			vo.setParking(rs.getString("parking"));
			vo.setContent(rs.getString("content"));
			vo.setPrice(rs.getString("price"));
			vo.setScore(rs.getDouble("score"));
			vo.setHit(rs.getInt("hit"));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		return vo;
	}
	
	// Cookie 가져오기
	public FoodVO foodCookieData(int fno) {
		FoodVO vo = new FoodVO();
		try {
			getConnection();
			String sql = "SELECT fno, name, poster FROM food_menupan WHERE fno = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, fno);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			vo.setFno(rs.getInt("fno"));
			vo.setName(rs.getString("name"));
			vo.setPoster("https://www.menupan.com" + rs.getString("poster"));
			
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnection();
		}
		return vo;
	}
	
	// 음식 종류별 검색
	public List<FoodVO> foodTypeFind(String type, int page) {
		List<FoodVO> list = new ArrayList<FoodVO>();
		
		try {
			getConnection();
			String sql = "";
			
			int rowSize = 12;
			int start = (rowSize * page) - (rowSize - 1);
			int end = rowSize * page;
			
			if (!type.equals("기타")) {
				sql = "SELECT fno, name, poster, num "
					+ "FROM (SELECT fno, name, poster, rownum as num "
					      + "FROM (SELECT fno, name, poster "
					            + "FROM food_menupan "
					            + "WHERE type LIKE '%'||?||'%')) "
					+ "WHERE num BETWEEN ? AND ?";
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, type);
				ps.setInt(2, start);
				ps.setInt(3, end);
			} else {
				sql = "SELECT fno, name, poster, num "
						+ "FROM (SELECT fno, name, poster, rownum as num "
						      + "FROM (SELECT fno, name, poster "
						            + "FROM food_menupan "
						            + "WHERE NOT REGEXP_LIKE(type, '한식|양식|중식|일식|카페'))) "
						+ "WHERE num BETWEEN ? AND ?";
				ps = conn.prepareStatement(sql);
				
				ps.setInt(1, start);
				ps.setInt(2, end);
			}
			
			
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				FoodVO vo = new FoodVO();
				
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster("https://www.menupan.com" + rs.getString(3));
				
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
	public int foodTypeTotalPage(String type) {
		int total = 0;
		
		try {
			getConnection();
			String sql = "";
			if (!type.equals("기타")) {
				sql = "SELECT CEIL(COUNT(*) / 12.0) "
				    + "FROM food_menupan "
				    + "WHERE type LIKE '%'||?||'%'";
				
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, type);
			} else {
				sql = "SELECT CEIL(COUNT(*) / 12.0) "
					+ "FROM food_menupan "
					+ "WHERE NOT REGEXP_LIKE(type, '한식|양식|중식|일식|카페')";
				
				ps = conn.prepareStatement(sql);
			}
			
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
	public List<FoodVO> foodFind(int page, String col, String fd){
		List<FoodVO> list = new ArrayList<FoodVO>();
		
		try {
			getConnection();
			String sql = "SELECT fno, name, poster, address, type, num "
					   + "FROM (SELECT fno, name, poster, address, rownum as num "
					         + "FROM (SELECT fno, name poster, address "
					               + "FROM food_menupan "
					               + "WHERE " + col + " LIKE '%'||?||'%')) " // col '' 일지 아닐지 모름 << 이거 다시 봐야함
					   + "WHERE num BETWEEN ? AND ?";
			
			ps = conn.prepareStatement(sql);
			
			int rowSize = 20;
			
			int start = (rowSize * page) - (rowSize -1);
			int end = (rowSize * page);
			
			ps.setString(1, fd);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				FoodVO vo = new FoodVO();
				
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster("https://www.menupan.com" + rs.getString(3));
				vo.setAddress(rs.getString(4));
				vo.setType(rs.getString(5));
				
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
	public int foodFindTotalPage(String col, String fd) {
		int total = 0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*) / 20.0) "
					   + "FROM food_menupan FROM food_menupan "
					   + "WHERE " + col + " LIKE '%'||?||'%'";

			ps = conn.prepareStatement(sql);
			
			ps.setString(1, fd);
			
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
}
