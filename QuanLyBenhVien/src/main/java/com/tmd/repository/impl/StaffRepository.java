package com.tmd.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tmd.dto.StaffDTO;
import com.tmd.repository.IStaffRepository;

@Repository
public class StaffRepository implements IStaffRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<StaffDTO> findAll() {
		
		return jdbcTemplate.query("select manv, holot, ten, taikhoan, mobile, email, trangthai, loai, matd, macv, madv"
				+ " from current.dmnhanvien",
				(rs, rowNum) -> new StaffDTO(
						rs.getString("manv"),
						rs.getString("holot"),
						rs.getString("ten"),
						rs.getString("taikhoan"),
						rs.getString("mobile"),
						rs.getString("email"),
						rs.getString("trangthai"),
						rs.getString("loai"),
						rs.getString("matd"),
						rs.getString("macv"),
						rs.getString("madv")
				));
	}

	@Override
	public long count() {
		
		return jdbcTemplate.queryForObject("select count(*) from current.dmnhanvien", Long.class);
	}

	@Override
	public List<StaffDTO> findAllForPaging(Integer limit, Integer offset) {
	
		String querySql = "select manv, holot, ten, taikhoan, mobile, email, trangthai, loai, matd, macv, madv"
							+ " from current.dmnhanvien "
							+ "LIMIT " + limit+" "
							+ "OFFSET " + offset;
		return jdbcTemplate.query(querySql,
			(rs, rowNum) -> new StaffDTO(
				rs.getString("manv"),
				rs.getString("holot"),
				rs.getString("ten"),
				rs.getString("taikhoan"),
				rs.getString("mobile"),
				rs.getString("email"),
				rs.getString("trangthai"),
				rs.getString("loai"),
				rs.getString("matd"),
				rs.getString("macv"),
				rs.getString("madv")
		));
	}

	@SuppressWarnings("deprecation")
	@Override
	public StaffDTO findByMaNv(String manv) {
		try {
			return (StaffDTO) jdbcTemplate.queryForObject("select manv, holot, ten, taikhoan, mobile, email, trangthai, loai, matd,"
					+ "	macv, madv"
					+ " from current.dmnhanvien where manv = ?", new Object[]{manv},
					(rs, rowNum) ->
	                Optional.of(new StaffDTO(
	        				rs.getString("manv"),
	        				rs.getString("holot"),
	        				rs.getString("ten"),
	        				rs.getString("taikhoan"),
	        				rs.getString("mobile"),
	        				rs.getString("email"),
	        				rs.getString("trangthai"),
	        				rs.getString("loai"),
	        				rs.getString("matd"),
	        				rs.getString("macv"),
	        				rs.getString("madv")
	        		)).get()
					);
		}
		catch(Exception ex) {
			return null;
		}
	}

	@Override
	public int save(StaffDTO staff) {

		int result = jdbcTemplate.update("insert into current.dmnhanvien(manv, holot, ten, taikhoan, loai, macv,"
				+ " matd, madv, trangthai, mobile, email, matkhau)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?)", 
				staff.getManv(), staff.getHolot(), staff.getTen(), staff.getTaikhoan(), staff.getLoai(), staff.getChucvu(),
				staff.getTrinhdo(), staff.getDonvi(), staff.getTrangthai(), staff.getMobile(), staff.getEmail(), staff.getMatkhau());
		return result;
	}

	@Override
	public int update(StaffDTO staff) {
		
		return jdbcTemplate.update("update current.dmnhanvien set holot = ?, ten = ?, loai = ?, macv = ?,"
				+ " matd = ?, madv = ?, trangthai = ?, mobile = ?, email = ?"
				+ " where manv = ?",
				staff.getHolot(), staff.getTen(), staff.getLoai(), staff.getChucvu(), staff.getTrinhdo(), staff.getDonvi(), 
				staff.getTrangthai(), staff.getMobile(), staff.getEmail(), staff.getManv());
	}

	@Override
	public Integer getMaxMaNv() {
		
		return jdbcTemplate.queryForObject("select max(manv) from current.dmnhanvien", Integer.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public StaffDTO findByTaiKhoan(String taikhoan) {
		try {
			return (StaffDTO) jdbcTemplate.queryForObject("select manv, holot, ten, taikhoan, mobile, email, trangthai, loai, matd,"
				+ "	macv, madv"
				+ " from current.dmnhanvien where taikhoan = ?", new Object[]{taikhoan},
				(rs, rowNum) ->
                Optional.of(new StaffDTO(
        				rs.getString("manv"),
        				rs.getString("holot"),
        				rs.getString("ten"),
        				rs.getString("taikhoan"),
        				rs.getString("mobile"),
        				rs.getString("email"),
        				rs.getString("trangthai"),
        				rs.getString("loai"),
        				rs.getString("matd"),
        				rs.getString("macv"),
        				rs.getString("madv")
        		)).get()
				);
		}
		catch(Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<StaffDTO> findAllBySearch(String contentSearch) {
		String querySql = "select manv, holot, ten, taikhoan, mobile, email, trangthai, loai, matd, macv, madv"
				+ " from current.dmnhanvien where ten ILIKE  ? or taikhoan ILIKE  ? or manv ILIKE  ?";
		return jdbcTemplate.query(querySql, new Object[] {contentSearch, contentSearch, contentSearch},
		(rs, rowNum) -> new StaffDTO(
			rs.getString("manv"),
			rs.getString("holot"),
			rs.getString("ten"),
			rs.getString("taikhoan"),
			rs.getString("mobile"),
			rs.getString("email"),
			rs.getString("trangthai"),
			rs.getString("loai"),
			rs.getString("matd"),
			rs.getString("macv"),
			rs.getString("madv")
		));
	}

}
