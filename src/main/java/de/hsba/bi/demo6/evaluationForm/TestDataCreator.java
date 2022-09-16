package de.hsba.bi.demo6.evaluationForm;

import de.hsba.bi.demo6.lecture.Lecture;
import de.hsba.bi.demo6.lecture.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataCreator {

   private final EvaluationFormService evaluationFormService;
   private final LectureService lectureService;

//  Soll automatisch nach dem Strat der Anwendung erledigt werden
    @EventListener(ApplicationStartedEvent.class)
    public void init(){
//  hinzufügen von Beispielen zum testen


        EvaluationForm evaluationForm = new EvaluationForm("Test-Evaluaionsbogen");
        evaluationFormService.addQuestion(evaluationForm,new Question(1, "Wie war das Modul?"));
        evaluationFormService.addQuestion(evaluationForm,new Question(2, "Wie war der Dozent?"));
        evaluationFormService.save(evaluationForm);

        Lecture lecture = lectureService.createLecture("Test-Lecture");
        lectureService.addEvaluationForm(evaluationForm,lecture);
        lectureService.save(lecture);
 }
}
