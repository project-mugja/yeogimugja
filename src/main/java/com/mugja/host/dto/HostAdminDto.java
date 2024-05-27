package com.mugja.host.dto;

import lombok.Data;

@Data
public class HostAdminDto {
	
	private int host_id;
	private String host_name;
	private String host_address;
	private String host_contact;
	private String host_intro;
//	private MultipartFile[] host_imgpath;
	
	
}
