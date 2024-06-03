package com.mugja.room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.mugja.room.dto.RoomDto;
import com.mugja.room.service.RoomServiceImpl;

@Controller
@RequestMapping("/mugja/admin")
public class RoomController {

	@Autowired
	private RoomServiceImpl service;
	
	@RequestMapping(value="/roomview/{hostid}",method = {RequestMethod.GET,RequestMethod.POST})
	public String  roomview(@PathVariable("hostid") int hostid,Model model) {
		System.out.println("hostid:" + hostid);
		model.addAttribute("hostid",hostid);
		return "/view/roomview";
	}
	
	
	//해당숙소 객실리스트
	@RequestMapping(value="/roomlist/{hostid}",method = {RequestMethod.GET,RequestMethod.POST})
	public String  roomlist(@PathVariable("hostid") int hostid,Model model) {
		
		model.addAttribute("list",service.roomlist(hostid));
		model.addAttribute("hostname",service.hostname(hostid));
		return "/view/roomlist";
	}
	
	
	
	@PostMapping("/roomplus")
    public String roomplus(RoomDto dto, @RequestParam("room_imgpath") MultipartFile[] files) {
        System.out.println(dto.getHost_id());

        service.hostRoomWrite(dto, files);
        
    	return "redirect:/mugja/admin/hostlist";
    }
}
