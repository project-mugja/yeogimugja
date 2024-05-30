package com.mugja.host.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mugja.host.dto.HostAdminDto;
import com.mugja.host.dto.HostImgDto;
import com.mugja.host.dto.HostTagDto;
import com.mugja.host.service.HostAdminListService;
import com.mugja.host.service.HostAdminServiceImpl;

@Controller
@RequestMapping("/mugja")
public class HostAdminController {

	@Autowired
	private HostAdminServiceImpl hostServiceImpl;
	
	@Autowired
	private HostAdminListService hostAdminListService;
	//@Autowired
	//private MybatisConfig mybatisconfig;
	
	@RequestMapping(value ="/admin/hostlist", method= {RequestMethod.GET,RequestMethod.POST})
	public String hostlist(Model model,HostAdminDto dto){
		List<HostAdminDto> list =  hostAdminListService.hostlist();
		model.addAttribute("list",list);
		
		return "/view/hostlist";
	}
	
	//숙소관리 상세페이지
	@RequestMapping(value ="/admin/hostdetail/{hostid}", method= {RequestMethod.GET,RequestMethod.POST})
	public String hostdetail(Model model,@PathVariable("hostid") String hostid){
		List<HostImgDto> hostImages = hostAdminListService.hostDetailImg(hostid);
		List<HostAdminDto> host = hostAdminListService.hostDetailHost(hostid);
		List<HostTagDto> hostTag = hostAdminListService.hostDetailTag(hostid);
		
		model.addAttribute("hostImages", hostImages);
		model.addAttribute("host",host);
		model.addAttribute("hostTag",hostTag);
	    
		
		return "/view/hostdetail";
	}
	@RequestMapping(value ="/admin/hostadd", method= {RequestMethod.GET,RequestMethod.POST})
	public String hostadd()	{
		
		return "/view/hostadd";
	}
	
	@RequestMapping(value = "/admin/roomadd", method = {RequestMethod.GET, RequestMethod.POST})
	public String roomadd(HostAdminDto dto,@ModelAttribute HostTagDto hashdto, @RequestParam("hostimgpath") MultipartFile[] files) {
		int hostid = hostServiceImpl.hostRoomWrite(dto,hashdto,files);
		
	    return "redirect:/mugja/admin/hostdetail/"+hostid;
	}
	
	//숙소 상세페이지 수정view
	@RequestMapping(value = "/admin/hostdetailupdate/{hostid}", method = {RequestMethod.GET, RequestMethod.POST})
	public String hostdetailupdate(Model model,@PathVariable("hostid") String hostid) {
		
		List<HostImgDto> hostImages = hostAdminListService.hostDetailImg(hostid);
		List<HostAdminDto> host = hostAdminListService.hostDetailHost(hostid);
		List<HostTagDto> hostTag = hostAdminListService.hostDetailTag(hostid);
		model.addAttribute("hostImages", hostImages);
		model.addAttribute("host",host);
		model.addAttribute("hostTag",hostTag);
		return "/view/hostdetailupdate";
	}
	
	// 숙소 상세페이지 수정 do
	@RequestMapping(value = "/admin/hostdetailupdatedo", method = {RequestMethod.GET, RequestMethod.POST})
	public String hostdetailupdatedo(HostAdminDto dto,@ModelAttribute HostTagDto hashdto,
				@RequestParam("hostimgpath") MultipartFile[] files) {
		System.out.println(dto.getHost_id());
		hostServiceImpl.hostRoomUpdate(dto, hashdto, files);
		return "redirect:/mugja/admin/hostdetail/"+dto.getHost_id();
	}
	
	//숙소 삭제
	@RequestMapping(value ="/admin/hostdelete/{hostid}", method = {RequestMethod.GET, RequestMethod.POST})
	public String hostdelete(@PathVariable("hostid") String hostid) {
		System.out.println(hostid + "호스트 아이디값");
		hostServiceImpl.hostdelete(hostid);
		
		System.out.println("삭제요청");
		
		return "redirect:/mugja/admin/hostlist";
	}
	
	
	}
