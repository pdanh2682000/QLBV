package com.tmd.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmd.repository.IUnitRepository;
import com.tmd.service.IUnitService;

@Service
public class UnitService implements IUnitService {

	@Autowired
	private IUnitRepository unitRepository;
	
	@Override
	public Map<String, String> findAllForMap() {
		
		return unitRepository.findAllForMap();
	}

}
