package de.hsba.bi.demo6.web;

import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EvaluationFormController {

    private EvaluationForm evaluationForm;

    public EvaluationFormController(){
//  Variable evaluationForm vom Typ evaluationForm wird erstellt
        evaluationForm= new EvaluationForm();
//  Ein paar Fragen zum testen hinzufügen
        evaluationForm.getQuestions().add(new Question(1,"Wie hat dir der Kurs gefallen?"));
        evaluationForm.getQuestions().add(new Question(2,"Wie hat dir der Dozent gefallen ?"));
    }

//  In der Methode "show" wird ein Model erstellt, welches evaluationForm heißt. In dieses wird die vorher erstellte Variable evaluationForm hinzugefügt
    @GetMapping("/")
    public String show(Model model){
        model.addAttribute("evaluationForm", evaluationForm);
        return "index";
    }

}
