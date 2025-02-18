package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
/*
 	POOL 메모리 주소 : java://comp/env
 		conn(주소) -> 오라클과 연결
 			사용중 true
 		conn(주소) -> 오라클과 연결
 			공백 false
 		conn(주소) -> 오라클과 연결
  
 */
public class EmpDAO {
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
	public List<EmpBean> empListData() {
		List<EmpBean> list = new ArrayList<EmpBean>();
				
		try {
			// 미리 생성된 Connection 주소를 얻어 온다
			getConnection();
			String sql = "SELECT empno, ename, job, hiredate, sal "
					   + "FROM emp";
			ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				EmpBean vo = new EmpBean();
				
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				
				list.add(vo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		
		return list;
	}
}
