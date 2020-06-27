package com.semi.auth.model.vo;

import java.sql.Date;

public class auth {
	private int signnum;         // 인증번호
	private String userid;       // 회원 아이디
	private int gno;             // 골 번호
	private String signcf;       // 골인원 사진인증
	private Date signdate;       // 인증 일자
	private String signtf;       // 인증 유효성
	public auth() {
		super();
		// TODO Auto-generated constructor stub
	}
	public auth(int signnum, String userid, int gno, String signcf, Date signdate, String signtf) {
		super();
		this.signnum = signnum;
		this.userid = userid;
		this.gno = gno;
		this.signcf = signcf;
		this.signdate = signdate;
		this.signtf = signtf;
	}
	public auth(String signcf) {
		super();
		this.signcf = signcf;
	}
	public int getsignnum() {
		return signnum;
	}
	public void setsignnum(int signnum) {
		this.signnum = signnum;
	}
	public String getuserid() {
		return userid;
	}
	public void setuserid(String userid) {
		this.userid = userid;
	}
	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	public String getsigncf() {
		return signcf;
	}
	public void setsigncf(String signcf) {
		this.signcf = signcf;
	}
	public Date getsigndate() {
		return signdate;
	}
	public void setsigndate(Date signdate) {
		this.signdate = signdate;
	}
	public String getsigntf() {
		return signtf;
	}
	public void setsigntf(String signtf) {
		this.signtf = signtf;
	}
	@Override
	public String toString() {
		return "auth [signnum=" + signnum + ", userid=" + userid + ", gno=" + gno + ", signcf=" + signcf + ", signdate="
				+ signdate + ", signtf=" + signtf + "]";
	}
	
	
}
