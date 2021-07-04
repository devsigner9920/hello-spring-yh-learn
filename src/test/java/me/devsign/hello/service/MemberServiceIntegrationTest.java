package me.devsign.hello.service;

import me.devsign.hello.domain.Member;
import me.devsign.hello.repository.MemberRepository;
import me.devsign.hello.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    void normalCaseJoin() {
        // given : 어떤 데이터를 기반으로 하는지 작성
        Member member = new Member();
        member.setName("hello");

        // when : 어떤 것을 대상으로 검증할지 작성
        Long saveId = memberService.join(member);

        // then : 검증부분
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void duplicateCaseJoin() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        // when
        memberService.join(member1);



        // then
        /*
        try ~ catch로 구현한 예외상황
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Already exist member.");
        }
        */

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("Already exist member.");

    }
}