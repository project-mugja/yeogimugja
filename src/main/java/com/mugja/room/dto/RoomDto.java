package com.mugja.room.dto;

import lombok.Data;

@Data
public class RoomDto {
	
	private int room_id;
	private int host_id;
	private String room_imgpath;
	private String room_name;
	private String room_maxcapacity;
	private String room_price;
//	private String room_img_id;
//	private String room_imgpath;
}
