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
		
		return jdbcTemplate.query("select manv, holot, ten, taikhoan, mobile, email, trangthai, loai, matd"
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
						rs.getString("matd")
				));
	}

	@Override
	public long count() {
		
		return jdbcTemplate.queryForObject("select count(*) from current.dmnhanvien", Long.class);
	}

	@Override
	public List<StaffDTO> findAllForPaging(Integer limit, Integer offset) {
	
		String querySql = "select manv, holot, ten, taikhoan, mobile, email, trangthai, loai, matd"
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
				rs.getString("matd")
		));
	}

	@SuppressWarnings("deprecation")
	@Override
	public StaffDTO findByMaNv(String manv) {
		
		return (StaffDTO) jdbcTemplate.queryForObject("select manv, holot, ten, taikhoan, mobile, email, trangthai, loai, matd"
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
        				rs.getString("matd")
        		)).get()
				);
	}

}
