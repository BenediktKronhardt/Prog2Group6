package de.hsba.bi.demo6.web;

import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormService;
import de.hsba.bi.demo6.evaluationForm.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/evaluationForms")
public class EvaluationFormController {

    private final EvaluationFormService evaluationFormService;

//  Abhängigkeit zu EvaluationFormService deklarieren
    public EvaluationFormController(EvaluationFormService evaluationFormService){
        this.evaluationFormService = evaluationFormService;
    }

    //  In der Methode "show" wird ein Model erstellt, welches evaluationForm heißt
    //  In dieses wird der EvaluationFormService hinzugefügt. Die Methode getAll() zeigt alle vorhandenen EvaluationForms an.
    @GetMapping
    public String index(Model model){
        model.addAttribute("evaluationForm", evaluationFormService.getAll());
        return "evaluationForms/index";
    }

    // Ein neues EvaluationForm-Objekt anlegen. Danach Weiterleitung auf die Seite des neuen Objektes
    @PostMapping
    public String create(String name){
        EvaluationForm evaluationForm = evaluationFormService.createEvaluationForm(name);
        return "redirect:/evaluationForms/" + evaluationForm.getId();
    }

//  Seite eines bestimmten EvaluationForm-Objektes anzeigen lassen
    @GetMapping(path="/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("evaluationForm", evaluationFormService.getEvaluationForm(id));
        return "evaluationForms/show";
    }

//  Ein Question-Objekt zu EvaluationForm hinzufügen
    @PostMapping(path="/{id}")
    public String addQuestion(@PathVariable("id") long id, Question question) {
        EvaluationForm evaluationForm = evaluationFormService.getEvaluationForm(id);
        evaluationFormService.addQuestion(evaluationForm, question);
        return "redirect:/evaluationForms/" +id;
    }
}