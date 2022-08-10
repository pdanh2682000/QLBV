package com.tmd.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.tmd.repository.IUnitRepository;

@Repository
public class ỤnitRepository implements IUnitRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, String> findAllForMap() {
		
		return jdbcTemplate.query("select madv, tendv from current.dmdonvi", (ResultSetExtractor<Map>) rs -> {
			HashMap<String,String> mapRet= new HashMap<String,String>();
			while(rs.next()) {
				mapRet.put(rs.getString("madv"), rs.getString("tendv"));
			}
			return mapRet;
		});
	}

}
