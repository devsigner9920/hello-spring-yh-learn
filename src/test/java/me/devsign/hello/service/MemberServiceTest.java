package me.devsign.hello.service;

import me.devsign.hello.domain.Member;
import static org.junit.jupiter.api.Assertions.*;

import me.devsign.hello.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {
    // 다음과 같이 초기화할 경우, member service내에서 초기화한 repository와 테스트케이스에서 초기화한 repository가 서로 상이할 수 있다.
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    // MemberService memberService = new MemberService();

    // 아래와 같이 선언할 경우, MemberService 내에 있는 repository와 테스트케이스에서 선언한 repository가 동일하게 동작한다.
    // Service에서 직접적으로 new 하여 선언하지 않는 설계방식이다.
    // 이와 같은 방법을 DI (Dependency Injection) 이라고 한다.
    MemoryMemberRepository memberRepository;
    MemberService memberService;

    // 각 테스트 실행 전에 호출된다.
    // 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고, 의존관계도 새로 맺어준다.
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

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