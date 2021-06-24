package me.devsign.hello.config;

import me.devsign.hello.repository.MemberRepository;
import me.devsign.hello.repository.MemoryMemberRepository;
import me.devsign.hello.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
