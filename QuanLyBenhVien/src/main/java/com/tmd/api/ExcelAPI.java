package com.tmd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmd.request.SearchAdvanceRequest;
import com.tmd.service.impl.ExcelService;

@Controller
@RequestMapping("/api/excel")
public class ExcelAPI {

	@Autowired
	ExcelService fileService;

	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/download")
	public ResponseEntity<Resource> getFile(
			@RequestParam(value = "contentValueSearch", required = false) String contentValueSearch) {
		String filename = "Danh_muc_nhan_vien.xlsx";
		InputStreamResource file;
		if(contentValueSearch == null || contentValueSearch.isEmpty())
			file = new InputStreamResource(fileService.load());
		else
			file = new InputStreamResource(fileService.load(contentValueSearch));
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping("/download")
	public ResponseEntity<Resource> getFileSearch(SearchAdvanceRequest request) {
		String filename = "Danh_muc_nhan_vien.xlsx";
		InputStreamResource file;
		if(request != null)
			file = new InputStreamResource(fileService.load(request));
		else
			file = new InputStreamResource(fileService.load());
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}
}
