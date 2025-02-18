package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//import com.sist.vo.FoodVO;

import java.io.*;

// XML 파싱
public class FoodDAO {
	private static SqlSessionFactory ssf; // 파싱 해주는 클래스
	// SqlSessionFactoryBean => Spring
	
//	 	XML 파싱
//	 	1. dom : XML 트리 형태로 저장 후 처리 => CRUD 가능 / 속도가 느림
//	 	2. sax : 태그를 한 개씩 읽어서 처리 => 읽기 전용 / 속도 빠름
	 
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
//			SELECT fno, name, poster, num 
// 			FROM (SELECT fno, name, poster, rownum as num 
// 		      	  FROM (SELECT fno, name, poster 
// 		            	FROM food_menupan ORDER BY fno ASC)) 
// 			WHERE num BETWEEN #{start} AND #{end}
//	 
	/*
	// 상세보기
	public static FoodVO foodDetailData(int fno) {
		ssf.openSession(true).update("hitIncrement", fno); // 조회수 증가 후
		// true -> autoCommit 그 외에는 commit 이 없는 상태
		return ssf.openSession().selectOne("foodDetailData", fno); // 상세보기 출력
	}
	// 목록 출력
	public static List<FoodVO> foodListData(Map map) {
		return ssf.openSession().selectList("foodListData", map);
	}
	public static int foodTotalPage() {
		return ssf.openSession().selectOne("foodTotalPage");
	}
	*/
}

