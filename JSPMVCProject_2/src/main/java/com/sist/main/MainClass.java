package com.sist.main;

import java.util.*;
import com.sist.model.*;

public class MainClass {
	public static void main(String[] args) {
		BoardModel bm = new BoardModel();
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
		System.out.print("URL 입력 : ");
		String url = scanner.next();
			
			if (url.equals("list.do")) {
				bm.boardListData();
			} else if (url.equals("detail.do")) {
				bm.boardDetailData();
			} else if (url.equals("insert.do")) {
				bm.boardInsert();
			} else if (url.equals("update.do")) {
				bm.boardUpdate();
			} else if (url.equals("delete.do")) {
				bm.boardDelete();
			}
		}
	}
}
