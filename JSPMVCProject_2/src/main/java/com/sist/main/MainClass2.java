package com.sist.main;

import java.lang.reflect.Method;
import java.util.*;
import com.sist.ann.*;
public class MainClass2 {
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		// XML 등록
		list.add("com.sist.model.BoardModel");
		list.add("com.sist.model.FoodModel");
		list.add("com.sist.model.GoodsModel");
		
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("URL 입력: ");
		String url = scanner.next();
		scanner.close();
		try {
			for (String cls : list) {
				
				Class<?> clsName = Class.forName(cls);
				Object obj = clsName.getDeclaredConstructor().newInstance();
				
				// BoardModel 에 설정한 모든 메서드를 읽는다
				Method[] methods = clsName.getDeclaredMethods();
				for (Method m : methods) {
					//System.out.println(m.getName());
					// 어노테이션 읽기
					RequestMapping rm = m.getAnnotation(RequestMapping.class);
					if (rm.value().equals(url)) {
						// 메서드 호출
						m.invoke(obj);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
