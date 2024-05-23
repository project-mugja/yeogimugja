package com.mugja.member.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MemberDto {

	private String mem_id; 
	private String mem_email;
	private String mem_pwd;
	private String mem_point;
	private String mem_grade;
	private String mem_payment;
}
