package com.tmd.converter;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmd.dto.StaffDTO;
import com.tmd.repository.ICategoryStaffRepository;
import com.tmd.repository.ILevelRepository;
import com.tmd.repository.IPositionRepository;
import com.tmd.repository.IStatusRepository;
import com.tmd.repository.IUnitRepository;

@Component
public class StaffConverter {

	@Autowired
	private IStatusRepository statusRepository;
	
	@Autowired
	private ICategoryStaffRepository categoryStaffRepository;
	
	@Autowired
	private IUnitRepository unitRepository;
	
	@Autowired
	private ILevelRepository levelRepository;
	
	@Autowired
	private IPositionRepository positionRepository; 
	
	public List<StaffDTO> toContentDTO(List<StaffDTO> listStaff) {
		Map<String, String> listStatus = statusRepository.findAll();
		Map<String, String> listCategoryStaff = categoryStaffRepository.findAllForMap();
		Map<String, String> listLevel = levelRepository.findAll();
		Map<String, String> listUnit = unitRepository.findAllForMap();
		Map<String, String> listPosition = positionRepository.findAllForMap();
		for(StaffDTO dto : listStaff) {
			dto.setTrangthai(listStatus.get(dto.getTrangthai()));
			dto.setLoai(listCategoryStaff.get(dto.getLoai()));
			dto.setTrinhdo(listLevel.get(dto.getTrinhdo()));
			dto.setDonvi(listUnit.get(dto.getDonvi()));
			dto.setChucvu(listPosition.get(dto.getChucvu()));
		}
		return listStaff;
	}
}