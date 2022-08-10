package com.tmd.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmd.service.impl.MD5HashService;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {

	@Autowired
	private MD5HashService md5;
	
	@RequestMapping("/admin")
	public ModelAndView homeAdmin() {
		ModelAndView mav = new ModelAndView("admin/home");
		md5.givenPassword_whenHashingUsingCommons_thenVerifying();
		return mav;
	}
	
	
}
