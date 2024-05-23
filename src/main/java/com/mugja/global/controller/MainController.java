package com.mugja.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mugja.member.service.SecurityService;

@Controller
public class MainController {
	
	@Autowired
	private SecurityService service;
	
	
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("id",service.userId());
		model.addAttribute("grade",service.userGrade());
		
		return "view/main";
	}
	
}
