package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;

import com.sist.vo.FoodVO;

import javax.naming.*;
/*
 	POOL 메모리 주소 : java://comp/env
 		conn(주소) -> 오라클과 연결
 			사용중 true
 		conn(주소) -> 오라클과 연결
 			공백 false
 		conn(주소) -> 오라클과 연결
  
 */
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	public void getConnection() {
		try {
			// 1. 탐색기
			Context init = new InitialContext();
			// 2. C 드라이버에 접근
			Context c = (Context)init.lookup("java://comp/env");
			// 3. 해당 Connection 의 정보
			DataSource ds = (DataSource)c.lookup("jdbc/oracle"); // Connection 의 정보를 모두 가져와서 conn 과 연결
			conn = ds.getConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void disconnection() {
		try {
			// ps, conn 자동으로 반환
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
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
	public FoodVO getFoodDetail(int fno, int mode) {
		FoodVO vo = new FoodVO();
		try {
			getConnection();
			if (mode == 1) {
				
				String sql = "UPDATE food_menupan SET hit = hit + 1 WHERE fno = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, fno);
			
			ps.executeUpdate();
			}
			
			String sql = "SELECT fno, name, type, phone, address, theme, poster, images, time, parking, content, price, score, hit "
					   + "FROM food_menupan "
					   + "WHERE fno = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, fno);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			vo.setFno(rs.getInt("fno"));
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
}
