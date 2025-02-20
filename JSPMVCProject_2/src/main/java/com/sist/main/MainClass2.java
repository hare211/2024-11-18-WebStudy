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
				
				Class<?> clsName = Class.forName(cls); // 클래스 정보 저장
				Object obj = clsName.getDeclaredConstructor().newInstance();
				
				// BoardModel 에 설정한 모든 메서드를 읽는다
				Method[] methods = clsName.getDeclaredMethods(); // clsName 에 저장된 클래스의 모든 메서드를 저장
				for (Method m : methods) { // 저장된 모든 메서드를 호출
					System.out.println(m.getName());
					// 어노테이션 읽기
					RequestMapping rm = m.getAnnotation(RequestMapping.class); // 
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
