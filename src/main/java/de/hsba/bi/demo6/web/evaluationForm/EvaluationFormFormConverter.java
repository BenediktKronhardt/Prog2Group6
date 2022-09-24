package de.hsba.bi.demo6.web.evaluationForm;


import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.Question;
import org.springframework.stereotype.Component;

@Component
public class EvaluationFormFormConverter {

    EvaluationFormForm toForm(EvaluationForm evaluationForm){
        EvaluationFormForm form = new EvaluationFormForm();
        form.setName(evaluationForm.getName());
        return form;
    }

    EvaluationForm update(EvaluationForm evaluationForm, EvaluationFormForm form){
        evaluationForm.setName(form.getName());
        return evaluationForm;
    }

    Question update(Question question, QuestionForm form){
        question.setText(form.getName());
        return question;
    }
}
