package com.tmd.service.impl;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmd.converter.StaffConverter;
import com.tmd.dto.StaffDTO;
import com.tmd.helper.excel.ExcelHelper;
import com.tmd.repository.impl.StaffRepository;
import com.tmd.request.SearchAdvanceRequest;

@Service
public class ExcelService {

	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	StaffConverter staffConverter;
		
	public ByteArrayInputStream load(String contentValueSearch) {
		List<StaffDTO> listStaff = staffRepository.findAllBySearch(contentValueSearch);
		listStaff = staffConverter.toContentDTO(listStaff);
		ByteArrayInputStream in = ExcelHelper.staffToExcel(listStaff);
		return in;
	}
	
	public ByteArrayInputStream load() {
		List<StaffDTO> listStaff = staffRepository.findAll();
		listStaff = staffConverter.toContentDTO(listStaff);
		ByteArrayInputStream in = ExcelHelper.staffToExcel(listStaff);
		return in;
	}
	
	public ByteArrayInputStream load(SearchAdvanceRequest request) {
		List<StaffDTO> listStaff = staffRepository.findAllBySearchAdvance(request);
		listStaff = staffConverter.toContentDTO(listStaff);
		ByteArrayInputStream in = ExcelHelper.staffToExcel(listStaff);
		return in;
	}
}
