package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import com.sist.vo.*;

public class BoardDAO {
	// XML 데이터를 저장하는 곳 
	private static SqlSessionFactory ssf;
	// SQlSession 은 SqlSessionFactory 가 생성
	// SqlSession session = ssf.openSession()
	// Connection / PreparedStatement 속해있음
	
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			
			ssf = new SqlSessionFactoryBuilder().build(reader); // build 안에 파싱한 것이 있음
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	// 게시글 출력
	public static List<BoardVO> boardListData(Map map) {
		SqlSession session = ssf.openSession(); // getConnection
		
		List<BoardVO> list = session.selectList("boardListData", map);
		
		session.close(); // disconnection
		
		return list;
	}
	// 총페이지
	public static int boardTotalPage() {
		SqlSession session = ssf.openSession(); // getConnection
		
		int total = session.selectOne("boardTotalPage");
		
		session.close(); // disconnection
		
		return total;
	}
	// 글 작성
	public static void boardInsert(BoardVO vo) {
		SqlSession session = ssf.openSession(true); // true -> autoCommit, false -> 
		
		session.insert("boardInsert", vo);
		// session.commit();
		session.close();
	}
}
