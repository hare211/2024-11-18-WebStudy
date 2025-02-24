package com.sist.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.*;
import com.sist.vo.*;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	// 메인페이지 상위 12개 출력
	public static List<FoodVO> foodTop12(String column) {
		SqlSession session = null;
		List<FoodVO> list = null;
		
		try {
			session = ssf.openSession();
			list = session.selectList("foodTop12", column);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
		/*
		SqlSession session = ssf.openSession();
		
		List<FoodVO> list = session.selectList("foodTop12", column);
		session.close();
		
		return list;
		*/
	}
	// 목록
	public static List<FoodVO> foodListData(Map<String, Integer> map) {
		SqlSession session = null;
		List<FoodVO> list = null;
		
		try {
			session = ssf.openSession();
			list = session.selectList("foodListData", map);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return list;
	}
	// 총페이지
	public static int foodTotalPage() {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("foodTotalPage");
		session.close();
		return total;
		
	}
	// 상세보기
	public static FoodVO foodDetailData(int fno) {
		// 조회수 증가
		SqlSession session = ssf.openSession();
		session.update("hitIncrement", fno);
		session.commit();
		// 상세보기
		FoodVO vo = session.selectOne("foodDetailData", fno); // fno -> mapper 에서 #{fno} 으로 준 매개변수
		session.close();
		return vo;
	}
	public static List<EmpVO> empListData() {
		SqlSession session = ssf.openSession();
		List<EmpVO> list = session.selectList("empListData");
		session.close();
		return list;
	}
}
