package de.hsba.bi.demo6.web.evaluationForm;


import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.lecture.Lecture;
import de.hsba.bi.demo6.lecture.LectureService;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "evaluationForms/index";
    }

    //  Ein neues evaluationForm-Objekt anlegen. Danach auf der gleichen Seite bleiben
//  Das ausgewählte Lecture-Objekt über das "select"-Element wird direkt dem Evaluationsbogen zugeprdnet (bzw. wird der Evaluationsbogen dem lecture-Objekt zugeordnet)


    @PostMapping
    public String create(@ModelAttribute("evaluationFormForm") EvaluationFormForm evaluationFormForm){
        EvaluationForm evaluationForm = evaluationFormService.save(formConverter.update(new EvaluationForm(), evaluationFormForm));
        return "redirect:/journals/" + evaluationForm.getId();
    }
}
