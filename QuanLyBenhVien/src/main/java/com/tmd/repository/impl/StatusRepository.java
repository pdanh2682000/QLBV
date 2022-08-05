package com.tmd.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.tmd.repository.IStatusRepository;

@Repository
public class StatusRepository implements IStatusRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, String> findAll() {
		
		return jdbcTemplate.query("select trangthai, diengiai from current.dmtrangthai", (ResultSetExtractor<Map>) rs -> {
			HashMap<String,String> mapRet= new HashMap<String,String>();
		    while(rs.next()){
		        mapRet.put(rs.getString("trangthai"),rs.getString("diengiai"));
		    }
		    return mapRet;
		});
	}

}
