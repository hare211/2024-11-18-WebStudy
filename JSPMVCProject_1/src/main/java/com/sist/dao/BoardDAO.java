package com.sist.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.BoardVO;

import java.io.*;
import java.util.List;
import java.util.Map;

public class BoardDAO {
	private static SqlSessionFactory ssf;
	// 초기화 => XML 에 등록된 데이터를 전송 => ssf
	static {
		try {
			// 1. XML 읽기
			Reader reader = Resources.getResourceAsReader("Config.xml"); // root 폴더(src/main/java -> classpath)에 존재할 시 경로 지정 하지 않아도 됨
			// 2. XML 파싱
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	// 게시글 목록
	public static List<BoardVO> boardListData(Map map) {
		// conn, ps
		SqlSession session = ssf.openSession();
		List<BoardVO> list = session.selectList("boardListData", map);
		session.close();
		return list;
	}
	// 총 페이지
	public static int boardTotalPage() {
		SqlSession session = ssf.openSession();
		int total  = session.selectOne("boardTotalPage");
		session.close();
		return total;
	}
	// 새 게시글 작성
	public static void boardInsert(BoardVO vo) {
		SqlSession session = ssf.openSession(true);
		session.insert("boardInsert", vo);
		session.close();
	}
	public static BoardVO boardDetailData(int no) {
		SqlSession session = ssf.openSession();
		// 조회수 증가
		session.update("hitIncrement", no);
		session.commit(); // UPDATE 절 이후 commit
		// 데이터 가져오기
		BoardVO vo = session.selectOne("boardDetailData", no);
		// 반환
		session.close();
		return vo;
	}
}
