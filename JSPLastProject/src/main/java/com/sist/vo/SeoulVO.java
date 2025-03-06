package com.sist.vo;

import lombok.Data;

@Data
public class SeoulVO {
	private int no, hit, likecount, replycount;
	private String title, poster, msg, address;
}
