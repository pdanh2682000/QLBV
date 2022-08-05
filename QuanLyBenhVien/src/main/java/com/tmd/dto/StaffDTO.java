package com.tmd.dto;

public class StaffDTO {

	private String manv;
	private String holot;
	private String ten;
	private String taikhoan;
	private String mobile;
	private String email;
	public String getManv() {
		return manv;
	}
	public void setManv(String manv) {
		this.manv = manv;
	}
	public String getHolot() {
		return holot;
	}
	public void setHolot(String holot) {
		this.holot = holot;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getTaikhoan() {
		return taikhoan;
	}
	public void setTaikhoan(String taikhoan) {
		this.taikhoan = taikhoan;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public StaffDTO(String manv, String holot, String ten, String taikhoan, String mobile, String email) {
		super();
		this.manv = manv;
		this.holot = holot;
		this.ten = ten;
		this.taikhoan = taikhoan;
		this.mobile = mobile;
		this.email = email;
	}
}
