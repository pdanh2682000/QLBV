package com.tmd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmd.dto.StaffDTO;
import com.tmd.repository.IStaffRepository;
import com.tmd.service.IStaffService;

@Service
public class StaffService implements IStaffService {

	@Autowired
	private IStaffRepository staffRepository;
	
	@Override
	public List<StaffDTO> findAll() {
		
		return staffRepository.findAll();
	}

	@Override
	public long count() {
		
		return staffRepository.count();
	}

	@Override
	public List<StaffDTO> findAllForPaging(Integer limit, Integer offset) {
		
		return staffRepository.findAllForPaging(limit, offset);
	}

}
