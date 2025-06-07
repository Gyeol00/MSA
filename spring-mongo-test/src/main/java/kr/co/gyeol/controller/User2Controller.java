package kr.co.gyeol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class User2Controller {

    @GetMapping("/user2/list")
    public String list() {
        return "/user2/list";
    }

    @GetMapping("/user2/register")
    public String register() {
        return "/user2/register";
    }

    @GetMapping("/user2/modify")
    public String modify() {
        return "/user2/modify";
    }
}
