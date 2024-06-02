package com.mugja.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mugja.member.service.SecurityService;

@Controller
@RequestMapping("/mugja")
public class PayController {
	@Autowired
	private SecurityService securityService; 
	
	@RequestMapping(value="/pay",method= {RequestMethod.GET,RequestMethod.POST})
	public String payview() {
		return "/view/payview";
	}
	@RequestMapping(value="/paytest",method= {RequestMethod.GET,RequestMethod.POST})
	public String paytest(Model model) {
		model.addAttribute("userid",securityService.userId());
		return "/view/paytest";
	}
	
	
}
