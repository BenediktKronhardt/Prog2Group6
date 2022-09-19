package de.hsba.bi.demo6.web;

import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormService;
import de.hsba.bi.demo6.lecture.Lecture;
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
        model.addAttribute("evaluationForm", evaluationFormService.getAll());
        return "lectures/showLecture";
    }

//  Hier kann ein EvaluationForm-Objekt, welches bisher noch keiner Lehrveranstaltung zugeordnet ist, einer Lehrveranstaltung zugeordnet werden.
//  Zuerst wird das Lecture-Objekt über die Id in showLecture definiert, dann das EvaluationForm-Objekt über den Value im Form-Objekt definiert und dieses zu dem lecture-Objekt hinzugefügt
    @PostMapping(path="/{id}")
    public String addEvaluationFormToLecture(@PathVariable("id") Long id, Long evaluationFormId){
        Lecture lecture = lectureService.getLecture(id);
        EvaluationForm evaluationForm = evaluationFormService.getEvaluationForm(evaluationFormId);
        lectureService.addEvaluationForm(evaluationForm, lecture);
        return "redirect:/lectures/" +id;
    }
}
