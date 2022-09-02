package de.hsba.bi.demo6.web;

import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormService;
import de.hsba.bi.demo6.evaluationForm.Question;
import de.hsba.bi.demo6.lecture.Lecture;
import de.hsba.bi.demo6.lecture.LectureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/evaluationForms")
public class EvaluationFormController {

    private final EvaluationFormService evaluationFormService;
    private final LectureService lectureService;

//  Abhängigkeit zu EvaluationFormService deklarieren
    public EvaluationFormController(EvaluationFormService evaluationFormService, LectureService lectureService){
        this.evaluationFormService = evaluationFormService;
        this.lectureService = lectureService;
    }

    //  In der Methode "show" wird ein Model erstellt, welches evaluationForm heißt
    //  In dieses wird der EvaluationFormService hinzugefügt. Die Methode getAll() zeigt alle vorhandenen EvaluationForms an.
    @GetMapping
    public String index(Model model){
        model.addAttribute("evaluationForm", evaluationFormService.getAll());
        model.addAttribute("lecture", lectureService.getAll());
        return "evaluationForms/index";
    }

    // Ein neues evaluationForm-Objekt anlegen. Danach auf der gleichen Seite bleiben
    @PostMapping
    public String create_evaluationForm(String name){
        evaluationFormService.createEvaluationForm(name);
        return "redirect:/evaluationForms/";
    }

    // Ein neues lecture-Objekt anlegen. Danach auf der gleichen Seite bleiben
    @PostMapping(path="/lecture")
    public String create_lecture(String lecture_name){
        lectureService.createLecture(lecture_name);
        return "redirect:/evaluationForms/";
    }

//  Seite eines bestimmten EvaluationForm-Objektes anzeigen lassen
    @GetMapping(path="/{id}")
    public String show_evaluationForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("evaluationForm", evaluationFormService.getEvaluationForm(id));
        return "evaluationForms/showEvaluationForm";
    }

//  Ein Question-Objekt zu EvaluationForm hinzufügen
    @PostMapping(path="/{id}")
    public String addQuestion(@PathVariable("id") long id, Question question) {
        EvaluationForm evaluationForm = evaluationFormService.getEvaluationForm(id);
        evaluationFormService.addQuestion(evaluationForm, question);
        return "redirect:/evaluationForms/" +id;
    }

//  Ein EvaluationForm-Objekt löschen, danach erfolgt eine Weiterleitung zur index-Seite
    @PostMapping(path="{id}/delete")
    public String delete(@PathVariable("id") Long id){
        evaluationFormService.delete(id);
        return "redirect:/evaluationForms/";
    }
}