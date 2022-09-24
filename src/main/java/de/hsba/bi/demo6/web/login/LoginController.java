package de.hsba.bi.demo6.web.login;

import de.hsba.bi.demo6.web.lecture.LectureForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {


    @GetMapping
    public String index(Model model){
        model.addAttribute("loginForm", new loginForm());
        return "login/index";
    }

}
