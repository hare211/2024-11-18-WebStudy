package com.sist.mybatis;


import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class EmpDAO {
	private static SqlSessionFactory ssf;
	
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml"); // config.xml 읽기
			ssf = new SqlSessionFactoryBuilder().build(reader);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static List<EmpVO> empListData(){
		return ssf.openSession().selectList("empListData");
	}
}
