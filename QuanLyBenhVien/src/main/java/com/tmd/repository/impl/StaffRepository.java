package com.tmd.repository.impl;

import java.util.List;

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
		
		return jdbcTemplate.query("select manv, holot, ten, taikhoan, mobile, email from current.dmnhanvien",
				(rs, rowNum) -> new StaffDTO(
						rs.getString("manv"),
						rs.getString("holot"),
						rs.getString("ten"),
						rs.getString("taikhoan"),
						rs.getString("mobile"),
						rs.getString("email")
				));
	}

	@Override
	public long count() {
		
		return jdbcTemplate.queryForObject("select count(*) from current.dmnhanvien", Long.class);
	}

	@Override
	public List<StaffDTO> findAllForPaging(Integer limit, Integer offset) {
	
		String querySql = "select manv, holot, ten, taikhoan, mobile, email"
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
				rs.getString("email")
		));
	}

}
