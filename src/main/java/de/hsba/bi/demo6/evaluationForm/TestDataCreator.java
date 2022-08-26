package de.hsba.bi.demo6.evaluationForm;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataCreator {

   private final EvaluationFormService evaluationFormService;

//  Soll automatisch nach dem Strat der Anwendung erledigt werden
    @EventListener(ApplicationStartedEvent.class)
    public void init(){
//  hinzufügen von Beispielen zum testen

        EvaluationForm evaluationForm = new EvaluationForm();
        evaluationForm.setName("20A-BI2-PROG2");
        evaluationFormService.addQuestion(evaluationForm,new Question(1, "Wie war das Modul?"));
        evaluationFormService.addQuestion(evaluationForm,new Question(2, "Wie war der Dozent?"));
        evaluationFormService.save(evaluationForm);
   }
}
