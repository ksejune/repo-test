package com.semi.reviews.model.vo;

import java.sql.Date;

public class reviews {
	private String userid;   // 유저아이디
	private int gno;         // 골 번호
	private Date rdate;      // 리뷰 작성일
	private String rcontent; // 리뷰 작성내용
	public reviews() {
		super();
		// TODO Auto-generated constructor stub
	}
	public reviews(String userid, int gno, Date rdate, String rcontent) {
		super();
		this.userid = userid;
		this.gno = gno;
		this.rdate = rdate;
		this.rcontent = rcontent;
	}
	public reviews(String rcontent) {
		super();
		this.rcontent = rcontent;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	@Override
	public String toString() {
		return "reviews [userid=" + userid + ", gno=" + gno + ", rdate=" + rdate + ", rcontent=" + rcontent + "]";
	}
	
	
}
