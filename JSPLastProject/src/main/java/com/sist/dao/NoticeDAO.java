package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.*;

public class NoticeDAO {
private static SqlSessionFactory ssf;
	
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
	public static List<NoticeVO> noticeListData(Map map) {
		SqlSession session = ssf.openSession();
		List<NoticeVO> list = session.selectList("noticeListData", map);
		session.close();
		return list;
	}
	public static int noticeTotalPage() {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("noticeTotalPage");
		session.close();
		return total;
	}
	public static void noticeInsert(NoticeVO vo) {
		SqlSession session = ssf.openSession(true);
		session.insert("noticeInsert", vo);
		session.close();
	}
	public static NoticeVO noticeDetailData(int no, int mode) {
		SqlSession session = ssf.openSession();
		if (mode == 1) {
			session.update("noticeHitIncrement", no);
			session.commit();
		}
		NoticeVO vo = session.selectOne("noticeDetailData", no);
		session.close();
		return vo;
	}
}
