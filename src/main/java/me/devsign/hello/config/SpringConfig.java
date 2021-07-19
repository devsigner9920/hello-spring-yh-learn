package me.devsign.hello.config;

import me.devsign.hello.repository.JdbcMemberRepository;
import me.devsign.hello.repository.JdbcTemplateMemberRepository;
import me.devsign.hello.repository.JpaMemberRepository;
import me.devsign.hello.repository.MemberRepository;
import me.devsign.hello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
// 스프링의 Dependencies Injection을 사용하면 기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.
// 아래와 같은 코드를 개방 폐쇄 원칙(OCP, Open-Closed Principle)이 잘 지켜지고 있다고 할 수 있는 코드다.
@Configuration
public class SpringConfig {
    //private DataSource dataSource;
    //private EntityManager em;
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//
//        //return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
