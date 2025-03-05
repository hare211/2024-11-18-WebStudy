package com.sist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.FoodVO;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
	public static FoodVO foodMainHouseData() {
		SqlSession session = ssf.openSession();
		FoodVO vo = session.selectOne("foodMainHouseData");
		session.close();
		return vo;
	}
	public static List<FoodVO> foodMainHouseData8() {
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodMainHouseData8");
		session.close();
		return list;
	}
}
