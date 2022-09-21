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

    EvaluationFormEntryForm toForm(Question entry){
        EvaluationFormEntryForm form = new EvaluationFormEntryForm();
        form.setId(entry.getId());
        form.setEvaluationForm(entry.getEvaluationForm());
        form.setText(entry.getText());
//        form.setCountQuestion(entry.getCountQuestion());
        return form;
    }

    Question update(Question entry, EvaluationFormEntryForm form){
        entry.setEvaluationForm(form.getEvaluationForm());
        entry.setText(form.getText());
//        entry.setCountQuestion(form.getCountQuestion());
//        entry.setId(form.getId());
        return entry;
    }
}
