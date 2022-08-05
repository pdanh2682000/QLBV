package com.tmd.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmd.repository.ICategoryStaffRepository;
import com.tmd.service.ICategoryStaffService;

@Service
public class CategoryStaffService implements ICategoryStaffService {

	@Autowired
	private ICategoryStaffRepository categoryStaffRepository;
	
	@Override
	public Map<String, String> findAllForMap() {
		
		return categoryStaffRepository.findAllForMap();
	}

}
