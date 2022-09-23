package de.hsba.bi.demo6.web.evaluationForm;


import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.lecture.Lecture;
import de.hsba.bi.demo6.lecture.LectureService;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Controller
@RequestMapping("/evaluationForms")
@RequiredArgsConstructor
public class EvaluationFormIndexController {

    private final LectureService lectureService;
    private final EvaluationFormService evaluationFormService;
    private final EvaluationFormFormConverter formConverter;

    //  In der Methode "show" wird ein Model erstellt, welches evaluationForm heißt
    //  In dieses wird der EvaluationFormService hinzugefügt. Die Methode getAll() zeigt alle vorhandenen EvaluationForms an.
    @GetMapping
    public String index(Model model){
        model.addAttribute("evaluationForm", evaluationFormService.getAll());
        model.addAttribute("lecture", lectureService.getAll());
        model.addAttribute("evaluationFormForm", new EvaluationFormForm());
        model.addAttribute("questionForm", new QuestionForm());
        return "evaluationForms/index";
    }

    //  Ein neues evaluationForm-Objekt anlegen. Danach auf der gleichen Seite bleiben
    //  Das ausgewählte Lecture-Objekt über das "select"-Element wird direkt dem Evaluationsbogen zugeprdnet (bzw. wird der Evaluationsbogen dem lecture-Objekt zugeordnet)
    @PostMapping
    // Wenn der eingegebene Name des Evaluationsbogens leer ist, passiert nichts und man wird auf die Startseite redirected
    public String create(@ModelAttribute("evaluationFormForm") @Valid EvaluationFormForm evaluationFormForm, BindingResult evaluationFormBinding){
        if (evaluationFormBinding.hasErrors()){
            return "redirect:/evaluationForms";
        }
        // Wenn ein neues EvaluationForm-Objekt angelegt wird, wird man direkt auf die show Seite dieses weitergeleitet
        EvaluationForm evaluationForm = evaluationFormService.save(formConverter.update(new EvaluationForm(), evaluationFormForm));
        // Ordnet das Lecture Objekt zu einem Bogen über die lecture_id hinzu
        // Wenn Lecture Objekt ausgewählt ist (lecture_id != null), dann Lecture Object zu EVF hinzufügen
        if (evaluationFormForm.getLecture_id() != null){
            Lecture lecture = lectureService.getLecture(evaluationFormForm.getLecture_id());
            lectureService.addEvaluationForm(evaluationForm, lecture);
        }
        return "redirect:/evaluationForms/" + evaluationForm.getId();
    }
}
