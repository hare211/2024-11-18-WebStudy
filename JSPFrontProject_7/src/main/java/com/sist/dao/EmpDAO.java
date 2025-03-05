package com.sist.dao;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.*;
import java.io.*;

public class EmpDAO {
	private static SqlSessionFactory ssf;
	
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static List<EmpVO> empListData() {
		SqlSession session = ssf.openSession();
		List<EmpVO> list = session.selectList("empListData");
		session.close();
		return list;
	}
	public static List<FoodVO> foodTypeListData(String type) {
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodTypeListData", type);
		session.close();
		return list;
	}
	public static List<EmpVO> empDeptJoinData() {
		SqlSession session = ssf.openSession();
		List<EmpVO> list = session.selectList("empDeptJoinData");
		session.close();
		return list;
	}
	public static List<EmpVO> empDeptSubqueryData() {
		SqlSession session = ssf.openSession();
		List<EmpVO> list = session.selectList("empDeptSubqueryData");
		session.close();
		return list;
	}
}
