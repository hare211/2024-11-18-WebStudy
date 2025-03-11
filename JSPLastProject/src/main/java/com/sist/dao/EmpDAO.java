package com.sist.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.*;
import com.sist.vo.*;

public class EmpDAO {
	private static SqlSessionFactory ssf;
	
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	public static List<EmpVO> empGetEnameData() {
		SqlSession session = ssf.openSession();
		List<EmpVO> list = session.selectList("empGetEnameData");
		session.close();
		return list;
	}
	public static List<EmpVO> empFindData(Map map) {
		SqlSession session = ssf.openSession();
		List<EmpVO> list = session.selectList("empFindData", map);
		session.close();
		return list;
	}
	
	
}
