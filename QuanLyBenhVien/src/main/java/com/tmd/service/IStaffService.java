package com.tmd.service;

import java.util.List;

import com.tmd.dto.StaffDTO;
import com.tmd.request.SearchAdvanceRequest;

public interface IStaffService {

	List<StaffDTO> findAll();
	List<StaffDTO> findAllForPaging(Integer limit, Integer offset);
	List<StaffDTO> findAllForSearch(String contentSearch);
	List<StaffDTO> findAllBySearchAdvance(SearchAdvanceRequest request);
	StaffDTO findByMaNv(String manv);
	long count();
	int save(StaffDTO staff);
	int update(StaffDTO staff);
}
