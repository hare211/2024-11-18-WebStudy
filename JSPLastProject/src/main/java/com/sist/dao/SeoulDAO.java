package com.sist.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.*;
import com.sist.vo.*;

public class SeoulDAO {
	private static SqlSessionFactory ssf;
	
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
	public static List<SeoulVO> seoulListData(Map<String, Object> map) {
		SqlSession session = ssf.openSession();
		List<SeoulVO> list = session.selectList("seoulListData", map);
		session.close();
		return list;
	}
	public static int seoulTotalPage(Map<String, Object> map) {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("seoulTotalPage", map);
		session.close();
		return total;
	}
}
