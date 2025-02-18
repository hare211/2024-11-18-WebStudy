package com.sist.dao;
/*
  	DAO : 오라클 연동
  	VO : 데이터를 모아서 한 번에 전송
  	Model : 
 
 */
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

import com.sist.vo.EmpVO;

public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	public void getConnection() {
		try {
			Context init = new InitialContext();
			
			Context c = (Context)init.lookup("java://comp/env");
			
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			
			conn = ds.getConnection();
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
	
	public List<EmpVO> empListData() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		try {
			getConnection();
			String sql = "SELECT empno, ename, job, hiredate, sal FROM emp";
			
			ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				EmpVO vo = new EmpVO();
				
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnection();
		}
		
		return list;
		
	}
}
