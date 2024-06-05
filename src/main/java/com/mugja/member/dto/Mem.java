package com.mugja.member.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "MEM")
public class Mem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "MEM_ID")
    private Integer memberId; // 회원번호 PK

    @Column(name = "MEM_EMAIL", unique = true)
    private String memberEmail;

    @Column(name = "MEM_PWD", nullable = false)
    private String memberPwd;

    @Column(name = "MEM_POINT", nullable = false)
    private Integer memberPoint;

    @Column(name = "MEM_GRADE", nullable = false)
    private String memberGrade;

}
