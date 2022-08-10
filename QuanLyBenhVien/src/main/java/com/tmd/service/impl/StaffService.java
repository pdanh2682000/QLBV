package com.tmd.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmd.dto.StaffDTO;
import com.tmd.repository.ICategoryStaffRepository;
import com.tmd.repository.ILevelRepository;
import com.tmd.repository.IPositionRepository;
import com.tmd.repository.IStaffRepository;
import com.tmd.repository.IStatusRepository;
import com.tmd.repository.IUnitRepository;
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
	private IUnitRepository unitRepository;
	
	@Autowired
	private ILevelRepository levelRepository;
	
	@Autowired
	private IPositionRepository positionRepository; 
	
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
		Map<String, String> listUnit = unitRepository.findAllForMap();
		Map<String, String> listPosition = positionRepository.findAllForMap();
		List<StaffDTO> results =  staffRepository.findAllForPaging(limit, offset);
		for(StaffDTO dto : results) {
			dto.setTrangthai(listStatus.get(dto.getTrangthai()));
			dto.setLoai(listCategoryStaff.get(dto.getLoai()));
			dto.setTrinhdo(listLevel.get(dto.getTrinhdo()));
			dto.setDonvi(listUnit.get(dto.getDonvi()));
			dto.setChucvu(listPosition.get(dto.getChucvu()));
		}
		return results;
	}

	@Override
	public StaffDTO findByMaNv(String manv) {
		
		return staffRepository.findByMaNv(manv);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int save(StaffDTO staff) {
		
		// hash password
		if(!staff.getMatkhau().isEmpty()) {
			String hashPassword = staff.getMatkhau().trim().replace("-", "");
			staff.setMatkhau(DigestUtils.md5Hex(hashPassword).toLowerCase());
		}
		
		// next manv
		if(staff.getManv() == null) {
			Integer maxMaNv = staffRepository.getMaxMaNv() + 1;
			staff.setManv(maxMaNv.toString());
		}
		
		// exist row
		if(staffRepository.findByMaNv(staff.getManv()) != null)
			return 0;
		
		// exist taikhoan
		if(staffRepository.findByTaiKhoan(staff.getTaikhoan()) != null)
			return -1;
		
		return staffRepository.save(staff);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int update(StaffDTO staff) {
		
		return staffRepository.update(staff);
	}

	@Override
	public List<StaffDTO> findAllForSearch(String contentSearch) {
		
		Map<String, String> listStatus = statusRepository.findAll();
		Map<String, String> listCategoryStaff = categoryStaffRepository.findAllForMap();
		Map<String, String> listLevel = levelRepository.findAll();
		Map<String, String> listUnit = unitRepository.findAllForMap();
		Map<String, String> listPosition = positionRepository.findAllForMap();
		List<StaffDTO> results = new ArrayList<>();
		results.addAll(staffRepository.findAllBySearch(contentSearch));
		for(StaffDTO dto : results) {
			dto.setTrangthai(listStatus.get(dto.getTrangthai()));
			dto.setLoai(listCategoryStaff.get(dto.getLoai()));
			dto.setTrinhdo(listLevel.get(dto.getTrinhdo()));
			dto.setDonvi(listUnit.get(dto.getDonvi()));
			dto.setChucvu(listPosition.get(dto.getChucvu()));
		}
		return results;
	}

}
