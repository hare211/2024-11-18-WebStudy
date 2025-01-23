package com.sist.main;

import java.util.*;

import oracle.jdbc.OracleTypes;

import java.sql.*;
public class StudentDAO {
	private Connection conn;
	// Procedure 호출 시 사용
	private CallableStatement cs;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static StudentDAO dao;
	
	public StudentDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static StudentDAO newInstance() {
		if (dao == null) {
			dao = new StudentDAO();
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
			if (cs != null) {
				cs.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	// 기능
	/*
	 
	CREATE OR REPLACE PROCEDURE studentAllData(
    	pResult OUT SYS_REFCURSOR
	)
	IS
	BEGIN
    	OPEN pResult FOR
        	SELECT * FROM student;
	END;
/
	 */
	// 1. 목록 출력
	public List<StudentVO> studentAllData() {
		List<StudentVO> list = new ArrayList<StudentVO>();
		
		try {
			getConnection();
			String sql = "{CALL studentAllData(?)}";
			
			// 전송
			cs = conn.prepareCall(sql);
			
			// ? 값 채우기
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			// 정수 : INTEGER, VARCHAR(VARCHAR2 아님), CURSOR
			// CURSOR : 여러 데이터형을 가지고 있는 데이터 집합을 가지고 있는 하나의 데이터형
			
			// 모든 실행 INSERT / UPDATE / DELETE 
			cs.executeQuery();
			
			// 실행
			// CURSOR 로 받을 시 반드시 ResultSet 으로 받아야 함
			// MyBatis 는 Map 을 한 번 거쳐야 함
			ResultSet rs = (ResultSet)cs.getObject(1); // ResultSet 변환, 1 -> 첫 번째 ?
			
			while (rs.next()) {
				StudentVO vo = new StudentVO();
				
				vo.setHakbun(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setKor(rs.getInt(3));
				vo.setEng(rs.getInt(4));
				vo.setMath(rs.getInt(5));
				
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
	// 2. 등록
	// 3. 수정
	// 4. 삭제
	// 5. 상세
	public StudentVO studentDetailData(int hakbun) {
		StudentVO vo = new StudentVO();
		try {
			getConnection();
			String sql = "{CALL studentDetailData(?, ?)}";
			
			cs = conn.prepareCall(sql);
			
			// ? 값 채우기
			// IN 매개 변수는 동일(?, ?)
			cs.setInt(1, hakbun);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			
			// 실행
			cs.executeQuery();
			
			// 결과 받아오기
			ResultSet rs = (ResultSet)cs.getObject(2); // 저장된 위치 매칭시키는 것 주의!!
			
			rs.next();
			
			vo.setHakbun(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setKor(rs.getInt(3));
			vo.setEng(rs.getInt(4));
			vo.setMath(rs.getInt(5));
			
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		
		
		return vo;
	}
			
}
