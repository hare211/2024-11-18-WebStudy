package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.ChefVO;
import com.sist.vo.RecipeVO;

public class RecipeDAO {
	private static SqlSessionFactory ssf;
	
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
	public static ChefVO recipeTodayChef() {
		ChefVO vo = null;
		SqlSession session = null;
		
		try {
			session = ssf.openSession();
			vo = session.selectOne("recipeTodayChef");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
		}
		return vo;
	}
	public static List<RecipeVO> recipeData7() {
		List<RecipeVO> list = null;
		SqlSession session = null;
		
		try {
			session = ssf.openSession();
			list = session.selectList("recipeData7");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
		}
		return list;
	}
	public static List<RecipeVO> recipeListData(Map map) {
		List<RecipeVO> list = null;
		SqlSession session = null;
		
		try {
			session = ssf.openSession();
			list = session.selectList("recipeListData");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
		}
		return list;
	}
	public static int recipeTotalPage() {
		int total = 0;
		SqlSession session = null;
		
		try {
			session = ssf.openSession();
			total = session.selectOne("recipeTotalPage");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
		}
		return total;
	}
}
