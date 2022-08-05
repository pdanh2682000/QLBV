package com.tmd.dto;

public class StaffDTO {

	private String manv;
	private String holot;
	private String ten;
	private String taikhoan;
	private String matkhau;
	private String mobile;
	private String email;
	private String trangthai;
	private String loai;
	private String trinhdo;
	
	public String getMatkhau() {
		return matkhau;
	}
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
	public String getTrinhdo() {
		return trinhdo;
	}
	public void setTrinhdo(String trinhdo) {
		this.trinhdo = trinhdo;
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
	}
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
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
	public StaffDTO(String manv, String holot, String ten, String taikhoan,
					String mobile, String email, String trangthai, String loai,
					String trinhdo) {
		super();
		this.manv = manv;
		this.holot = holot;
		this.ten = ten;
		this.taikhoan = taikhoan;
		this.mobile = mobile;
		this.email = email;
		this.trangthai = trangthai;
		this.loai = loai;
		this.trinhdo = trinhdo;
	}
	public StaffDTO() {
		super();
	}
	
	
}
