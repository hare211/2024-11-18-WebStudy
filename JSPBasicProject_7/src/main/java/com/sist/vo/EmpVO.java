package com.sist.vo;
/*
 	JSP => 사용자 요청에 따른 데이터 출력
 		=> <% %> <%= %>
 		=> page / taglib
 		=> 내장 객체
 			request / response / session / cookie
		=> HTML / CSS / JavaScript
		
	DAO => DBCP / MyBatis / JPA
 */

import java.util.Date;

public class EmpVO {
	private int empno, sal;
	private String ename, job;
	private Date hiredate;
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	
	// 사용 가능하게 기능 부여
	
}
