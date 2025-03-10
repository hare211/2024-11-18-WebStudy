package com.sist.vo;

import lombok.Data;

@Data
public class MemberVO {
	private String id, pwd, name, sex, birthday, email, post, addr1, addr2, phone, content, admin, msg;
}
