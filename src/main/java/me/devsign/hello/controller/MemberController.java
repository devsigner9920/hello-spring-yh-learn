package me.devsign.hello.controller;

import me.devsign.hello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// Controller Annotation : 스프링 컨테이너에 annotation이 사용된 컨트롤러 객체를 담아둔다.
// 이와 같은 것들을 스프링 컨테이너에서 빈이 관리 된다라고 표현한다.

// Component Scan : Application 실행 클래스가 포함된 패키지부터 하위 패키지까지 Component를 스캔하여 스프링 빈을 생성한다.
@Controller
public class MemberController {

    private MemberService memberService;
    // DI에는 필드 주입, setter 주입, 생성자 주입 이렇게 3가지 방법이 있다. 의존관계가 실행중에
    // 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
