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
	public static MemberVO memberLogin(String id, String pwd) {
		SqlSession session = ssf.openSession();
		MemberVO vo = new MemberVO();
		int count = session.selectOne("memberIdCheckCount", id);
		if (count == 0) {
			vo.setMsg("NOID");
		} else {
			vo = session.selectOne("memberGetPassword", id);
			if (pwd.equals(vo.getPwd())) {
				vo.setMsg("OK");
			} else {
				vo.setMsg("NOPWD");
			}
		}
		session.close();
		return vo;
	}
	public static MemberVO memberInfoData(String id) {
		SqlSession session = ssf.openSession();
		MemberVO vo = session.selectOne("memberInfoData", id);
		session.close();
		return vo;
	}
}
