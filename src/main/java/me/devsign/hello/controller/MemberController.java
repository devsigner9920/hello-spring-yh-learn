package me.devsign.hello.controller;

import me.devsign.hello.domain.Member;
import me.devsign.hello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// Controller Annotation : 스프링 컨테이너에 annotation이 사용된 컨트롤러 객체를 담아둔다.
// 이와 같은 것들을 스프링 컨테이너에서 빈이 관리 된다라고 표현한다.
// 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다.
// 같은 스프링 빈이면 모두 같은 인스턴스다.
// 설정으로 싱글톤이 아니게 설정할 수 있지만, 특별한 경우를 제외하곤 대부분 싱글톤으로 사용된다.

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

    @GetMapping("members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
