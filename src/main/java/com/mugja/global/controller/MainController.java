package com.mugja.global.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mugja.member.service.SecurityService;

@Controller
public class MainController {
	
	@Autowired
	private SecurityService service;
    @Autowired
    private HttpSession httpSession;


	@GetMapping("/")
	public String main(Model model, HttpServletRequest req) {
		model.addAttribute("id",service.userId());
		model.addAttribute("grade",service.userGrade());

		req.getSession().setAttribute("user","들어감");

		System.out.println("메인 컨트롤러"+service.userId());
		return "view/main";
	}
	
}
