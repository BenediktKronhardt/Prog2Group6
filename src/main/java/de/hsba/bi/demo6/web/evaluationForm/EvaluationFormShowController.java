package de.hsba.bi.demo6.web.evaluationForm;



import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;


import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.Question;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormService;
import de.hsba.bi.demo6.lecture.LectureService;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;


@Controller
@RequestMapping("/evaluationForms/{id}")
@RequiredArgsConstructor
public class EvaluationFormShowController {

    private final EvaluationFormService evaluationFormService;

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
        model.addAttribute("evaluationFormEntryForm", new QuestionForm());

        return "evaluationForms/showEvaluationForm";
    }

    @PostMapping
    public String change(Model model, @PathVariable("id") Long id, @ModelAttribute("evaluationFormForm") @Valid EvaluationFormForm evaluationFormForm, BindingResult evaluationFormBinding){
       //Wenn der neue Name leer ist, kann der Name nicht geändert werden
        if (evaluationFormBinding.hasErrors()){
           model.addAttribute("evaluationFormEntryForm", new QuestionForm());
           return "evaluationForms/showEvaluationForm";
       }

        EvaluationForm evaluationForm = getEvaluationForm(id);
        evaluationFormService.save(formConverter.update(evaluationForm, evaluationFormForm));
        return "redirect:/evaluationForms/" +id;
    }

// Eine Frage lästt sich zum EvaluationsBogen hinzufügen
    @PostMapping(path = "/questions")
    public String addQuestion(Model model, @PathVariable("id") Long id,
                              @ModelAttribute("evaluationFormEntryForm") @Valid QuestionForm questionForm, BindingResult questionBinding){
        EvaluationForm evaluationForm = getEvaluationForm(id);
        // Wenn das Question Objekt einen Error wirft (keinen Inhalt), dann wird keine Frage hinzugefügt
        if (questionBinding.hasErrors()){
            model.addAttribute("evaluationFormForm", formConverter.toForm(evaluationForm));
            return "evaluationForms/showEvaluationForm";
        }
        evaluationFormService.addQuestion(evaluationForm, formConverter.update(new Question(), questionForm));

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