package de.hsba.bi.demo6.evaluationForm;

import de.hsba.bi.demo6.lecture.Lecture;
import de.hsba.bi.demo6.lecture.LectureService;
import de.hsba.bi.demo6.user.User;
import de.hsba.bi.demo6.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataCreator {

   private final EvaluationFormService evaluationFormService;
   private final LectureService lectureService;
   private final UserService userService;
   private final PasswordEncoder passwordEncoder;

//  Soll automatisch nach dem Strat der Anwendung erledigt werden
    @EventListener(ApplicationStartedEvent.class)
    public void init(){
//  hinzufügen von Beispielen zum testen


        EvaluationForm evaluationForm = new EvaluationForm("Test-EvaluationFormMitLecture");

        evaluationFormService.addQuestion(evaluationForm,new Question(1, "Wie war das Modul?"));
        evaluationFormService.addQuestion(evaluationForm,new Question(2, "Wie war der Dozent?"));
        evaluationFormService.save(evaluationForm);

        EvaluationForm evaluationForm2 = new EvaluationForm("Test-EvaluationFormOhneLecture");
        evaluationFormService.addQuestion(evaluationForm2,new Question(1, "Wie war das Modul?"));
        evaluationFormService.addQuestion(evaluationForm2,new Question(2, "Wie war der Dozent?"));
        evaluationFormService.save(evaluationForm2);

        Lecture lecture = lectureService.createLecture("Test-Lecture");
        lectureService.addEvaluationForm(evaluationForm,lecture);
        lectureService.save(lecture);

        // Erstellt die vorher festgelegten Nutzer
        createUser("Otto", "su", User.USER_ROLE);
        createUser("Admin", "su", User.ADMIN_ROLE);



 }
 // Erstellt neuen Nutzer in Datenbank (nur für den Admin nötig)
    private User createUser(String name, String password, String role){
        return userService.save(new User(name, passwordEncoder.encode(password), role));
    }

}
