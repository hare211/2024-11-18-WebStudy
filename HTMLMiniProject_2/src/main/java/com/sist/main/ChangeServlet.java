package com.sist.main;

public class ChangeServlet {
	private static String[] pages = {
			"",
			"MusicList",
			"MusicDetail",
			"MusicGenreFind",
			"MusicFind"
	};
	
	public static String pageChange(int mode) {
		
		return pages[mode];
	}
			
	public enum page{
		FoodList,
		FoodDetail,
		FoodTypeFind,
		FoodFind
	}
	
}
