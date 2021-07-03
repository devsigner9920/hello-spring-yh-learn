package me.devsign.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // 스프링 컨테이너에서 루트 컨텍스트 패스에 매핑되는 컨트롤러가 존재하지 않다면 static 리소스에 있는 index.html 파일을 파싱한다.
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
