package com.tmd.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tmd.constant.SystemConstant;
import com.tmd.dto.StaffDTO;
import com.tmd.output.OutputResponse;
import com.tmd.service.ICategoryStaffService;
import com.tmd.service.ILevelService;
import com.tmd.service.IStaffService;

@Controller(value = "staffControllerOfAdmin")
public class StaffController {

	@Autowired
	private IStaffService staffService;
	
	@Autowired
	private ICategoryStaffService categoryStaffService;
	
	@Autowired
	private ILevelService levelService;
	
	
	@RequestMapping("admin/staff/list")
	public ModelAndView listStaff(	@RequestParam(value = "page", required = false) Integer page,
									@RequestParam(value = "limit", required = false) Integer limit) {
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
		return mav;
	}
	
	@RequestMapping("/admin/staff/edit")
	public ModelAndView editStaff(@RequestParam(value = "manv", required = false) String manv) {
		ModelAndView mav = new ModelAndView("admin/staff/edit");
		if(manv == null)
			mav.addObject(new StaffDTO());
		else
			mav.addObject(staffService.findByMaNv(manv));
		mav.addObject("categoryStaffs", categoryStaffService.findAllForMap());
		mav.addObject("levels", levelService.findAllForMap());
		return mav;
	}
}
