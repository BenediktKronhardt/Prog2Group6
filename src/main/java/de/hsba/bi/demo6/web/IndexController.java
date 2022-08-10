package de.hsba.bi.demo6.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @GetMapping("/")
    //neuer Parameter vom Typ Model: nimmt Daten für den View auf (per model.addAttribute(...))
    //neuer Parameter mit der Annotation @Requestparam: definiert einen optionalen Request-Parameter namens hello
    public String index(Model model, @RequestParam(name="hello", required = false) String hello) {
        if (hello!=null){
            model.addAttribute("greeting", "Hello, "+hello+ "!");
        }
        return "index";
    }
}
