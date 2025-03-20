package com.sist.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CartVO {
	private int cno, gno, account, price;
	private String id;
	private Date regdate;
	private GoodsVO gvo = new GoodsVO();
	private MemberVO mvo = new MemberVO();
}
