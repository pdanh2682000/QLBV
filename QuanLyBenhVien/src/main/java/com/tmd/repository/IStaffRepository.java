package com.tmd.repository;

import java.util.List;

import com.tmd.dto.StaffDTO;

public interface IStaffRepository {

	List<StaffDTO> findAll();
	List<StaffDTO> findAllForPaging(Integer limit, Integer offset);
	StaffDTO findByMaNv(String manv);
	long count();
	
}
