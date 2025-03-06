package com.sist.dao;

import java.util.List;
import java.util.Map;

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
	public static List<FoodVO> foodListData(Map<String, Integer> map) {
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodListData", map);
		session.close();
		return list;
	}
	public static int foodTotalPage() {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("foodTotalPage");
		session.close();
		return total;
	}
	public static FoodVO foodDetailData(int fno) {
		SqlSession session = ssf.openSession();
		session.update("foodHitIncrement", fno);
		session.commit();
		FoodVO vo = session.selectOne("foodDetailData", fno);
		session.close();
		return vo;
	}
}
