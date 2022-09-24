package de.hsba.bi.demo6.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class IndexController {


    public String index() {
        return "redirect:/evaluationForms/";
    }

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecruityContextHolder.gertContext().getAuthentication();
    }
}