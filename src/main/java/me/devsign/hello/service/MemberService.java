package me.devsign.hello.service;

import me.devsign.hello.domain.Member;
import me.devsign.hello.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service는 비즈니스에 의존적으로 설계하는 것을 지향한다.
// 비즈니스 로직에 적절하게 메소드 네이밍한다.
public class MemberService {
    private final MemberRepository memberRepository;

    // Dependency Injection
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // Optional한 형태로 반환됨
                // Optional.ifPresent는 만약에 값이 있다면 값을 반환하여 m에 삽입하고 중괄호 내 로직을 처리
                // null일 경우 중괄호 내 로직 무시
                .ifPresent(m -> {
                    throw new IllegalStateException("Already exist member.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * ID로 특정 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
