package com.mugja.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "MEM")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEM_ID")
    private int memberId; //회원번호(PK)

    @Column(name = "MEM_EMAIL")
    private String memberEmail; //이메일

    @Column(name = "MEM_PWD")
    private String memberPwd; //비밀번호

    @Column(name = "MEM_POINT")
    private int memberPoint; //보유포인트

    @Column(name = "MEM_GRADE")
    private String memberGrade; //회원등급

    @Builder
    public Member(int memberId, String memberEmail, String memberPwd, int memberPoint, String memberGrade) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberPwd = memberPwd;
        this.memberPoint = memberPoint;
        this.memberGrade = memberGrade;
    }
}