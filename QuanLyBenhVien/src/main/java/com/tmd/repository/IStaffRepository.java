package com.tmd.repository;

import java.util.List;

import com.tmd.dto.StaffDTO;

public interface IStaffRepository {

	List<StaffDTO> findAll();
	List<StaffDTO> findAllForPaging(Integer limit, Integer offset);
	StaffDTO findByMaNv(String manv); // unique
	StaffDTO findByTaiKhoan(String taikhoan); // unique
	List<StaffDTO> findAllBySearch(String contentSearch);
	long count();
	Integer getMaxMaNv();
	int save(StaffDTO staff);
	int update(StaffDTO staff);
	
}
