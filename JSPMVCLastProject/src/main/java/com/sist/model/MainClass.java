package com.sist.model;

import java.io.File;

public class MainClass {
	public static void main(String[] args) {
		String path = "C:\\Users\\sist-117\\git\\2024-11-18-WebStudy\\JSPMVCLastProject\\src\\main\\java\\";
		String pack ="com.sist.model";
		
		path = path + pack.replace(".", "\\");
		
		try {
			File dir = new File(path);
			System.out.println("dir: " + dir); //pack 변수를 .com 이 아니라 com 으로 시작해서 에러 났었음
			File[] files = dir.listFiles();
			
			for (File file : files) {
				
				String name = file.getName();
				String ext = name.substring(name.lastIndexOf(".") + 1);
				if (ext.equals("java")) {
					//System.out.println(file.getName()); // 확장자가 java 일 때만 가져온다
					String clsName = name.substring(0, name.lastIndexOf("."));
					String packName = pack + "." + clsName;
					System.out.println(packName);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
