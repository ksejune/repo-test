package com.semi.point.model.vo;

import java.sql.Date;

public class point {
	private String userid;  // 회원 아이디
	private Date paydate;   // 결제일자
	private int price;      // 금액
	
	public point() {
		super();
		// TODO Auto-generated constructor stub
	}
	public point(String userid, Date paydate, int price) {
		super();
		this.userid = userid;
		this.paydate = paydate;
		this.price = price;
	}
	public point(int price) {
		super();
		this.price = price;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getPaydate() {
		return paydate;
	}
	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "point [userid=" + userid + ", paydate=" + paydate + ", price=" + price + "]";
	}
	
	
}
