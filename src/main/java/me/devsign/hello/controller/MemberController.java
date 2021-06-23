package me.devsign.hello.controller;

import me.devsign.hello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// Controller Annotation : 스프링 컨테이너에 annotation이 사용된 컨트롤러 객체를 담아둔다.
// 이와 같은 것들을 스프링 컨테이너에서 빈이 관리 된다라고 표현한다.

@Controller
public class MemberController {


    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
