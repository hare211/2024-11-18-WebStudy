package com.sist.vo;
import java.util.*;

import lombok.Data;
@Data
public class ReplyVO {
	private int rno, mno;
	private String id, name, msg, dbday;
	private Date regdate;
}
