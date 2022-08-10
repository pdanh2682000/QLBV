package com.tmd.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmd.repository.IStatusRepository;
import com.tmd.service.IStatusService;

@Service
public class StatusService implements IStatusService {

	@Autowired
	private IStatusRepository statusRepository;
	
	@Override
	public Map<String, String> findAllForMap() {
		
		return statusRepository.findAll();
	}

}