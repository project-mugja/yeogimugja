package com.mugja.mugja;

import com.mugja.member.domain.Member;
import com.mugja.member.domain.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void saveMember() {
        Member saveParams = Member.builder()
                .memberEmail("user02@test.com")
                .memberPwd("1357")
                .memberPoint(100)
                .memberGrade("일반")
                .build();

        Member member = memberRepository.save(saveParams);
        Assertions.assertEquals(member.getMemberEmail(), "user02@test.com");
    }

    //전체 회원 조회
    @Test
    void findAllMember() {
        memberRepository.findAll();
    }

    //회원 상세정보 조회
    @Test
    void findMemberById() {
        Member member = memberRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException());
        Assertions.assertEquals(member.getMemberEmail(), "user02@test.com");
    }

    //회원 정보 삭제
    @Test
    void deleteMemberById() {
        memberRepository.deleteById(1L);
    }
}
