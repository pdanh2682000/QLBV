package com.tmd.service;

import java.util.List;

import com.tmd.dto.StaffDTO;

public interface IStaffService {

	List<StaffDTO> findAll();
	List<StaffDTO> findAllForPaging(Integer limit, Integer offset);
	List<StaffDTO> findAllForSearch(String contentSearch);
	StaffDTO findByMaNv(String manv);
	long count();
	int save(StaffDTO staff);
	int update(StaffDTO staff);
}
