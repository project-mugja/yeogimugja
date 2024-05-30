package com.mugja.host.dto;

import lombok.Data;

@Data
public class HostAdminDto {
	
	private int host_id;
	private String host_name;
	private String host_address;
	private String host_contact;
	private String host_intro;
	private String host_address_detail;
	private float host_lat; //위도
	private float host_lng; //경도
	private String host_imgpath;
	
	
}
