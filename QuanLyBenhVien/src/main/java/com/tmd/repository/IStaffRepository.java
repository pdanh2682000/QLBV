package com.tmd.repository;

import java.util.List;

import com.tmd.dto.StaffDTO;

public interface IStaffRepository {

	List<StaffDTO> findAll();
	long count();
	List<StaffDTO> findAllForPaging(Integer limit, Integer offset);
}
