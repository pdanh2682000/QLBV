package com.tmd.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmd.repository.IPositionRepository;
import com.tmd.service.IPositionService;

@Service
public class PositionService implements IPositionService {

	@Autowired
	private IPositionRepository positionRepository;
	
	@Override
	public Map<String, String> findAllForMap() {
		
		return positionRepository.findAllForMap();
	}

}
