package com.mugja.host.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mugja.host.dto.HostAdminDto;
import com.mugja.host.dto.HostTagDto;
import com.mugja.host.service.HostAdminServiceImpl;

@Controller
@RequestMapping("/admin")
public class HostAdminController {

	@Autowired
	private HostAdminServiceImpl hostServiceImpl;
	
	@RequestMapping(value ="/hostadd", method= {RequestMethod.GET,RequestMethod.POST})
	public String hostadd()	{
		
		return "/view/hostadd";
	}
	
	@RequestMapping(value = "/roomadd", method = {RequestMethod.GET, RequestMethod.POST})
	public String roomadd(HostAdminDto dto,@ModelAttribute HostTagDto hashdto, @RequestParam("hostimgpath") MultipartFile[] files) {
		hostServiceImpl.hostRoomWrite(dto,hashdto,files);
		System.out.println(hashdto.getTag1());
		System.out.println(hashdto.getTag2());
		System.out.println(hashdto.getTag3());
		
		
		String[] tag1 = hashdto.getTag1();
		for(String a : tag1) {
			System.out.println(a);
		}
		
	    return "redirect:/admin/hostadd";
	}
	}
