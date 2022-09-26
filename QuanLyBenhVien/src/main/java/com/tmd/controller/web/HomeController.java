package com.tmd.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tmd.dto.TestDTO;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@RequestMapping("/")
	public ModelAndView homeWeb() {
		ModelAndView mav = new ModelAndView("web/hello");
		return mav;
	}
	
	@RequestMapping("/reload")
	public ModelAndView reload(@RequestParam(value = "contentValue", required = false) String contentValue) {
		ModelAndView mav = new ModelAndView("web/hello");
		if(contentValue != null) {
			if(contentValue.equalsIgnoreCase("increase"))
				TestDTO.number ++;
			else
				TestDTO.number --;
			mav.addObject("contentValue", contentValue);
		}
		return mav;
	}
}
