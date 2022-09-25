package de.hsba.bi.demo6.web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index() {
        return "redirect:/evaluationForms/";
    }

    @GetMapping("/login")
    public String login(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth instanceof AnonymousAuthenticationToken ? "login" : "redirect:/";
    }
}