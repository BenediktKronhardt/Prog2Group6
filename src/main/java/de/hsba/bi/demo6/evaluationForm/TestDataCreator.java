package de.hsba.bi.demo6.evaluationForm;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TestDataCreator {

    private final EvaluationFormRepository evaluationFormRepository;

    public TestDataCreator(final EvaluationFormRepository evaluationFormRepository) {
        this.evaluationFormRepository = evaluationFormRepository;
    }

//  Soll automatisch nach dem Strat der Anwendung erledigt werden
    @EventListener(ApplicationStartedEvent.class)
    public void init(){
//  hinzufügen von Beispielen zum testen
//  Hier muss ID noch automatisch generiert werden
//  TODO: ID automatisch generieren
        EvaluationForm evaluationForm = new EvaluationForm();
        evaluationForm.setName("20A-BI2-PROG2");
        evaluationForm.getQuestions().add(new Question(new Integer(15),"Wie war die Struktur des Moduls?"));
        evaluationForm.getQuestions().add(new Question(new Integer(16),"Wie war der Dozent?"));

        evaluationFormRepository.save(evaluationForm);
    }
}
