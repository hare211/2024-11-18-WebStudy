package com.sist.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
import com.sist.commons.*;

public class MemberDAO {
	private static SqlSessionFactory ssf;
	
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
	public static int memberIdcheck(String id) {
		SqlSession session = ssf.openSession();
		int count = session.selectOne("memberIdcheck", id);
		session.close();
		return count;
	}
	public static void memberInsert(MemberVO vo) {
		SqlSession session = ssf.openSession(true);
		session.insert("memberInsert", vo);
		session.close();
	}
}
