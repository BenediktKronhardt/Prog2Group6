package de.hsba.bi.demo6.web.evaluationForm;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.Question;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormService;
import de.hsba.bi.demo6.lecture.Lecture;
import de.hsba.bi.demo6.lecture.LectureService;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/evaluationForms/{id}")
@RequiredArgsConstructor
public class EvaluationFormShowController {

    private final EvaluationFormService evaluationFormService;
    private final LectureService lectureService;
    private EvaluationFormFormConverter formConverter;




    @ModelAttribute("evaluationForm")
    public EvaluationForm getEvaluationForm(@PathVariable("id")Long id){
        EvaluationForm evaluationForm = evaluationFormService.getEvaluationForm(id);
        return evaluationForm;
    }

// Nachher hier den Exceptionhandler einsetzen

    @GetMapping
    public String show(Model model){
        model.addAttribute("evaluationFormEntryForm", new EvaluationFormEntryForm());
        return "evaluationForms/showEvaluationForm";
    }

    @PostMapping
    public String addEntry(@PathVariable("id") Long id,
                           @ModelAttribute("evaluationFormEntryForm") EvaluationFormEntryForm entryForm){
        EvaluationForm evaluationForm = getEvaluationForm(id);
        evaluationFormService.addQuestion(evaluationForm, formConverter.update(new Question(), entryForm));
        return "redirect:/evaluationForms/";
    }

//  Ein EvaluationForm-Objekt löschen, danach erfolgt eine Weiterleitung zur index-Seite
    @PostMapping(path="/delete")
    public String delete(@PathVariable("id") Long id){
        evaluationFormService.delete(id);
        return "redirect:/evaluationForms/";
    }
}