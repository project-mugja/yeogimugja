package com.mugja.room.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mugja.room.dto.RoomDto;

@Mapper
public interface RoomMapper {
	
	void roomwrite(RoomDto dto);
	
	@Insert("INSERT INTO room_img VALUES (null,#{room_imgpath},#{dto.room_id})")
	void hostRoomImgWrite(@Param("dto") RoomDto dto,@Param("room_imgpath") String storename);
	
	int roomid(RoomDto dto);
	//해당숙소 객실리스트
	List<RoomDto> roomList(int hostid);
	
	String roomImgList(int hostid);
	//호스트id체크
	int hostidcheck(RoomDto dto);
	
	String hostname(int hostid);
}
