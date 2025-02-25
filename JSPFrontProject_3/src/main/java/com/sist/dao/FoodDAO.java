package com.sist.dao;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.FoodVO;

import java.io.*;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	
	// XML 을 읽어서 필요한 데이터 추출 -> 파싱
	/*
	 	HTML: Jsoup
	 	XML: DocumentBuilder
	 	JSON: json-simple, jackson
	 */
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	// 목록
	public static List<FoodVO> foodFindData(Map map) {
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodFindData", map);
		session.close();
		return list;
	}
	// 총페이지
	public static int foodFindTotalpage(String address) {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("foodFindTotalPage", address);
		session.close();
		return total;
	}
	// 상세보기
	public static FoodVO foodFindDataDetailData(int fno) {
		SqlSession session = ssf.openSession();
		FoodVO vo = session.selectOne("foodFindDetailData", fno);
		session.close();
		return vo;
	}
}
