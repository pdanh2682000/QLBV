package com.tmd.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.tmd.repository.ILevelRepository;

@Repository
public class LevelRepository implements ILevelRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, String> findAll() {
		
		return jdbcTemplate.query("select matd, tentd from current.dmtrinhdo", (ResultSetExtractor<Map>) rs -> {
			HashMap<String, String> mapRet = new HashMap<>();
			while(rs.next()) {
				mapRet.put(rs.getString("matd"), rs.getString("tentd"));
			}
			return mapRet;
		});
	}
	
	
}