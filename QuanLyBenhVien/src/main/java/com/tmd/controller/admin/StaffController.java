package com.tmd.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tmd.constant.SystemConstant;
import com.tmd.dto.StaffDTO;
import com.tmd.output.OutputResponse;
import com.tmd.request.SearchAdvanceRequest;
import com.tmd.service.ICategoryStaffService;
import com.tmd.service.ILevelService;
import com.tmd.service.IPositionService;
import com.tmd.service.IStaffService;
import com.tmd.service.IStatusService;
import com.tmd.service.IUnitService;

@Controller(value = "staffControllerOfAdmin")
public class StaffController {

	@Autowired
	private IStaffService staffService;
	
	@Autowired
	private ICategoryStaffService categoryStaffService;
	
	@Autowired
	private ILevelService levelService;
	
	@Autowired
	private IPositionService positionService;
	
	@Autowired
	private IUnitService unitService;
	
	@Autowired
	private IStatusService statusService;
	
	
	@RequestMapping("admin/staff/list")
	public ModelAndView listStaff(	@RequestParam(value = "page", required = false) Integer page,
									@RequestParam(value = "limit", required = false) Integer limit,
									HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/staff/list");
		List<StaffDTO> listStaff = new ArrayList<>();
		OutputResponse<StaffDTO> out = new OutputResponse<>();
		out.setPage(SystemConstant.DEFAULT_PAGE);
		out.setLimit(SystemConstant.DEFAULT_LIMIT);
		if (page == null || limit == null)
			listStaff = staffService.findAllForPaging(out.getLimit(), out.getPage() - 1);
		else {
			listStaff = staffService.findAllForPaging(limit, (page - 1)*limit);
			out.setPage(page);
			out.setLimit(limit);
		}
		out.setResults(listStaff);
		out.setTotalItem(staffService.count());
		out.setTotalPage((int) Math.ceil((double) out.getTotalItem() / out.getLimit()));
		mav.addObject("list_staff", out);
		mav.addObject("menu", "menu_staff");
		if (request.getParameter("message") != null && request.getParameter("alert") != null) {
			mav.addObject("message", request.getParameter("message"));
			mav.addObject("alert", request.getParameter("alert"));
			mav.addObject("contentMessage", request.getParameter("contentMessage"));
		}
		return mav;
	}
	
	@RequestMapping("/admin/staff/edit")
	public ModelAndView editStaff(@RequestParam(value = "manv", required = false) String manv,
									HttpServletRequest request) {
		try {
			ModelAndView mav = new ModelAndView("admin/staff/edit");
			if(manv == null)
				mav.addObject(new StaffDTO());
			else
				mav.addObject(staffService.findByMaNv(manv));
			mav.addObject("categoryStaffs", categoryStaffService.findAllForMap());
			mav.addObject("levels", levelService.findAllForMap());
			mav.addObject("positions", positionService.findAllForMap());
			mav.addObject("units", unitService.findAllForMap());
			mav.addObject("statuses", statusService.findAllForMap());
			if (request.getParameter("message") != null && request.getParameter("alert") != null) {
				mav.addObject("message", request.getParameter("message"));
				mav.addObject("alert", request.getParameter("alert"));
				mav.addObject("contentMessage", request.getParameter("contentMessage"));
			}
			return mav;
		}
		catch(Exception e) {
			ModelAndView mav = new ModelAndView("redirect:/admin/staff/list");
			mav.addObject("message", "not_found_staff");
			mav.addObject("alert", "danger");
			mav.addObject("contentMessage", "Không tìm thấy nhân viên");
			return mav;
		}
	}
	
	@RequestMapping(value = "/admin/staff/edit", method = RequestMethod.POST)
	public ModelAndView doEditStaff(StaffDTO dto,
									@RequestParam(value = "manv", required = false) String manv) {
		ModelAndView mav;
		// update
		if(manv != null && !manv.isEmpty()) {
			mav = new ModelAndView("redirect:/admin/staff/edit?manv=" + manv);
			mav.addObject("message", "update_staff_success");
			mav.addObject("alert", "success");
			mav.addObject("contentMessage", "Cập nhật thông tin nhân viên thành công!");
			staffService.update(dto);
		}
		// save
		else {
			mav = new ModelAndView("redirect:/admin/staff/list");
			int status = staffService.save(dto);
			if(status == 0) {
				mav.addObject("message", "exist_staff");
				mav.addObject("alert", "danger");
				mav.addObject("contentMessage", "Đã tồn tại mã nhân viên trong dữ liệu!");
			}
			else if(status == -1) {
				mav.addObject("message", "exist_taikhoan_staff");
				mav.addObject("alert", "danger");
				mav.addObject("contentMessage", "Tên tài khoản đã tồn tại!");
			}
			else {
				mav.addObject("message", "create_staff_success");
				mav.addObject("alert", "success");
				mav.addObject("contentMessage", "Thêm mới nhân viên thành công!");
			}
		}
		return mav;
	}
	
	@RequestMapping("/admin/staff/search")
	public ModelAndView search(@RequestParam(value = "contentSearch", required = false) String contentSearch) {
		ModelAndView mav = new ModelAndView("admin/staff/list");
		List<StaffDTO> listStaff = new ArrayList<>();
		OutputResponse<StaffDTO> out = new OutputResponse<>();
		listStaff = staffService.findAllForSearch(contentSearch);
		out.setResults(listStaff);
		mav.addObject("list_staff", out);
		mav.addObject("menu", "menu_staff");
		mav.addObject("contentValueSearch", contentSearch);
		return mav;
	}
	
	@RequestMapping("/admin/staff/searchAdvance")
	public ModelAndView searchAdvance() {
		ModelAndView mav = new ModelAndView("admin/staff/search");
		mav.addObject(new SearchAdvanceRequest());
		mav.addObject("categoryStaffs", categoryStaffService.findAllForMap());
		mav.addObject("levels", levelService.findAllForMap());
		mav.addObject("positions", positionService.findAllForMap());
		mav.addObject("units", unitService.findAllForMap());
		mav.addObject("statuses", statusService.findAllForMap());
		mav.addObject("menu", "menu_staff");
		return mav;
	}
	
	@RequestMapping(value = "/admin/staff/searchAdvance", method = RequestMethod.POST)
	public ModelAndView doSearchAdvance(SearchAdvanceRequest request) {
		ModelAndView mav = new ModelAndView("admin/staff/search");
		List<StaffDTO> listStaff = new ArrayList<>();
		OutputResponse<StaffDTO> out = new OutputResponse<>();
		listStaff = staffService.findAllBySearchAdvance(request);
		out.setResults(listStaff);
		mav.addObject("list_staff", out);
		mav.addObject("menu", "menu_staff");
		mav.addObject("categoryStaffs", categoryStaffService.findAllForMap());
		mav.addObject("levels", levelService.findAllForMap());
		mav.addObject("positions", positionService.findAllForMap());
		mav.addObject("units", unitService.findAllForMap());
		mav.addObject("statuses", statusService.findAllForMap());
		return mav;
	}
}
