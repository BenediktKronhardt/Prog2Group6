package de.hsba.bi.demo6.web.lecture;


import de.hsba.bi.demo6.lecture.Lecture;
import de.hsba.bi.demo6.lecture.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureIndexController {

    private final LectureService lectureService;
    private final LectureFormConverter formConverter;


    @GetMapping
    public String index(){
        return "lectures/index";
    }


    // Ein neues lecture-Objekt anlegen. Danach auf der gleichen Seite bleiben
    @PostMapping
    public String create(@ModelAttribute("lectureForm") @Valid LectureForm lectureForm, BindingResult lectureBinding){
        if (lectureBinding.hasErrors()){
            return "redirect:/lectures";
        }
        Lecture lecture = lectureService.save(formConverter.update(new Lecture(),lectureForm));
        lectureService.save(lecture);
        return "redirect:/lectures/";
    }
}
