package de.hsba.bi.demo6.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
//    GetMapping definiert, welche GET-Requests eine Methode behandeln soll
    @GetMapping("/")
    //neuer Parameter vom Typ Model: nimmt Daten für den View auf (per model.addAttribute(...))
    //neuer Parameter mit der Annotation @Requestparam: definiert einen optionalen Request-Parameter namens hello
    public String index(Model model, @RequestParam(name="hello", required = false) String hello) {
        if (hello!=null){
            model.addAttribute("greeting", "Hello, "+hello+ "!");
        }
        return "index";
    }

//    für URL Aufruf /hello/Benedikt bekommt der Methoden Parameter name den Wert "Benedikt"
    @GetMapping(path="/hello/{name}")
    public String hello(@PathVariable String name, Model model){
        if (name !=null){
            model.addAttribute("greeting", "Hello "+name);
        }
        return "index";
    }

    @PostMapping(path="/hello")
    public String hello(Model model, @RequestParam String name){
        if (name != null) {
            model.addAttribute("greeting", "Hello, "+name+"!");
        }
        return "index";
    }
}
