package de.hsba.bi.demo6.web.evaluationForm;

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
public class EvaluationFormShowController {

    private final EvaluationFormService evaluationFormService;
    private final LectureService lectureService;

//  Abhängigkeit zu EvaluationFormService deklarieren
    public EvaluationFormShowController(EvaluationFormService evaluationFormService, LectureService lectureService){
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

//  Ein neues evaluationForm-Objekt anlegen. Danach auf der gleichen Seite bleiben
//  Das ausgewählte Lecture-Objekt über das "select"-Element wird direkt dem Evaluationsbogen zugeprdnet (bzw. wird der Evaluationsbogen dem lecture-Objekt zugeordnet)
    @PostMapping
    public String create_evaluationForm(String name, Long lecture_id){
        EvaluationForm evaluationForm = evaluationFormService.createEvaluationForm(name);
        Lecture lecture = lectureService.getLecture(lecture_id);
        lectureService.addEvaluationForm(evaluationForm, lecture);
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