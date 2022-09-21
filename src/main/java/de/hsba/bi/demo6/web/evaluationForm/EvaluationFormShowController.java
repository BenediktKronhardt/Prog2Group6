package de.hsba.bi.demo6.web.evaluationForm;

import java.util.List;


import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.Question;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormService;
import de.hsba.bi.demo6.lecture.Lecture;
import de.hsba.bi.demo6.lecture.LectureService;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;


@Controller
@RequestMapping("/evaluationForms/{id}")
@RequiredArgsConstructor
public class EvaluationFormShowController {

    private final EvaluationFormService evaluationFormService;
    private final LectureService lectureService;
    private final EvaluationFormFormConverter formConverter;




    @ModelAttribute("evaluationForm")
    public EvaluationForm getEvaluationForm(@PathVariable("id")Long id){
        EvaluationForm evaluationForm = evaluationFormService.getEvaluationForm(id);
        if (evaluationForm == null){
            throw new de.hsba.bi.demo6.web.NotFoundException();
        }
        return evaluationForm;
    }

// Nachher hier den Exceptionhandler einsetzen

    @GetMapping
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("evaluationFormForm", formConverter.toForm(getEvaluationForm(id)));
        model.addAttribute("evaluationFormEntryForm", new EvaluationFormEntryForm());
        return "evaluationForms/showEvaluationForm";
    }

    @PostMapping
    public String change(Model model, @PathVariable("id") Long id, @ModelAttribute("evaluationFormForm") @Valid EvaluationFormForm evaluationFormForm, BindingResult evaluationFormBinding){
       //Wenn der neue Name leer ist, kann der Name nicht geändert werden
        if (evaluationFormBinding.hasErrors()){
           model.addAttribute("evaluationFormEntryForm", new EvaluationFormEntryForm());
           return "evaluationForms/showEvaluationForm";
       }

        EvaluationForm evaluationForm = getEvaluationForm(id);
        evaluationFormService.save(formConverter.update(evaluationForm, evaluationFormForm));
        return "redirect:/evaluationForms/" +id;
    }

    @PostMapping(path = "/entries")
    public String addEntry(@PathVariable("id") Long id,
                           @ModelAttribute("evaluationFormEntryForm") EvaluationFormEntryForm entryForm){
        EvaluationForm evaluationForm = getEvaluationForm(id);
        evaluationFormService.addQuestion(evaluationForm, formConverter.update(new Question(), entryForm));
        return "redirect:/evaluationForms/{id}";
    }

//  Ein EvaluationForm-Objekt löschen, danach erfolgt eine Weiterleitung zur index-Seite
    @PostMapping(path="/delete")
    public String delete(@PathVariable("id") Long id){
        evaluationFormService.delete(id);
        return "redirect:/evaluationForms/";
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFound(){
        return "/notFound/";
    }
}