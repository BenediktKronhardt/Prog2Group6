package de.hsba.bi.demo6.web;

import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EvaluationController {

    private EvaluationForm evaluationForm;

    public EvaluationController(){
        evaluationForm= new EvaluationForm();
//  Ein paar Bögen zum testen hinzufügen
        evaluationForm.getQuestions().add(new Question(1,"Wie hat dir der Kurs gefallen?"));
        evaluationForm.getQuestions().add(new Question(2,"Wiehat dir der Dozent gefallen ?"));
    }

    @GetMapping("/")
    public String show(Model model){
        model.addAttribute("evaluationForm", evaluationForm);
        return "index";
    }

    @PostMapping("/")
    public String addQuestion(Question question){
        evaluationForm.getQuestions().add(question);

        return "redirect:/";
    }
}
