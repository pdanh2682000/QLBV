package com.tmd.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmd.repository.ILevelRepository;
import com.tmd.service.ILevelService;

@Service
public class LevelService implements ILevelService {

	@Autowired
	private ILevelRepository levelRepository;
	
	@Override
	public Map<String, String> findAllForMap() {
		
		return levelRepository.findAll();
	}

}
