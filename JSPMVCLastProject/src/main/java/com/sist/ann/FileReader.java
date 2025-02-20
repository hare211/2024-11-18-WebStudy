package com.sist.ann;

import java.util.*;
import java.io.*;

public class FileReader {
	public static List<String> componentScan(String path, String pack) {
		List<String> list = new ArrayList<String>();
		
		try {
			path = path + File.separator + pack.replace(".", File.separator);
			File dir = new File(path);
			// File 에 있는 것들 저장
			File[] files = dir.listFiles();
			for (File f : files) {
				//System.out.println("files : " + f.getName());
				String name = f.getName();
				String ext = name.substring(name.lastIndexOf(".") + 1); // class
				
				if (ext.equals("class")) {
					// SeoulMode.class
					String clsName = name.substring(0, name.lastIndexOf(".")); // 클래스 이름이 필요
					System.out.println("clsName : " + clsName);
					String packName = pack + "." + clsName;
					list.add(packName);
					System.out.println("packName : " + packName); // package.className
				}
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return list;
	}
} 
