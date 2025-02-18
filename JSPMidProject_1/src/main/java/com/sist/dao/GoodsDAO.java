package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.GoodsVO;

import java.io.*;
public class GoodsDAO {
	private static SqlSessionFactory ssf; // 관리자 => 연결 => SQL 문장 처리 => XML 파일을 파싱 요청
	static {
		try {				// ibatis.io.Resources
			Reader reader = Resources.getResourceAsReader("Config.xml");
			
			ssf = new SqlSessionFactoryBuilder().build(reader); // XML 파싱
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// 기능 처리
	// 목록
	public static List<GoodsVO> goodsListData(Map map) {
		return ssf.openSession().selectList("goodsListData", map);
	}
	// 총페이지
	public static int goodsTotalPage() {
		return ssf.openSession().selectOne("goodsTotalPage");
	}
}
