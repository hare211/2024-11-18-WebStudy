package com.sist.vo;

import java.util.Date;

import lombok.Data;
@Data
public class NoticeVO {
	private int no, hit, type;
	private String name, subject, content, dbday, types;
	private Date regdate;
}
