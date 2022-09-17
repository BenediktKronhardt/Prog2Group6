package de.hsba.bi.demo6.web;

import de.hsba.bi.demo6.evaluationForm.EvaluationFormService;
import de.hsba.bi.demo6.lecture.LectureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lectures")
public class LecturesController {

    private final LectureService lectureService;
    private final EvaluationFormService evaluationFormService;

    public LecturesController(EvaluationFormService evaluationFormService, LectureService lectureService){
        this.evaluationFormService = evaluationFormService;
        this.lectureService = lectureService;
    }

    @GetMapping
    public String index(Model model){
        return "lectures/index";
    }


    // Ein neues lecture-Objekt anlegen. Danach auf der gleichen Seite bleiben
    @PostMapping
    public String create_lecture(String lecture_name, Integer startyear, String course, Integer countStudents,Integer contacthours, String teacher){
        lectureService.createLecture(lecture_name, startyear,course,countStudents,contacthours,teacher);
        return "redirect:/lectures/";
    }

    //  Seite eines bestimmten Lecture-Objektes anzeigen lassen
    @GetMapping(path="/{id}")
    public String show_lecture(@PathVariable("id") long id, Model model) {
        model.addAttribute("lecture", lectureService.getLecture(id));
        return "lectures/showLecture";
    }
}
