package com.tmd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmd.dto.StaffDTO;
import com.tmd.repository.ICategoryStaffRepository;
import com.tmd.repository.ILevelRepository;
import com.tmd.repository.IStaffRepository;
import com.tmd.repository.IStatusRepository;
import com.tmd.service.IStaffService;

@Service
public class StaffService implements IStaffService {

	@Autowired
	private IStaffRepository staffRepository;
	
	@Autowired
	private IStatusRepository statusRepository;
	
	@Autowired
	private ICategoryStaffRepository categoryStaffRepository;
	
	@Autowired
	private ILevelRepository levelRepository;
	
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
		
		Map<String, String> listStatus = statusRepository.findAll();
		Map<String, String> listCategoryStaff = categoryStaffRepository.findAllForMap();
		Map<String, String> listLevel = levelRepository.findAll();
		List<StaffDTO> results =  staffRepository.findAllForPaging(limit, offset);
		for(StaffDTO dto : results) {
			dto.setTrangthai(listStatus.get(dto.getTrangthai()));
			dto.setLoai(listCategoryStaff.get(dto.getLoai()));
			dto.setTrinhdo(listLevel.get(dto.getTrinhdo()));
		}
		return results;
	}

	@Override
	public StaffDTO findByMaNv(String manv) {
		
		return staffRepository.findByMaNv(manv);
	}

}
