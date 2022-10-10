package de.hsba.bi.demo6.evaluationForm;

import de.hsba.bi.demo6.lecture.Lecture;
import de.hsba.bi.demo6.lecture.LectureService;
import de.hsba.bi.demo6.rating.RatingService;
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
   private final RatingService ratingService;
   private final PasswordEncoder passwordEncoder;

//  Soll automatisch nach dem Strat der Anwendung erledigt werden
    @EventListener(ApplicationStartedEvent.class)
    public void init(){
//  hinzufügen von Beispielen zum testen


        EvaluationForm projektmanagement = new EvaluationForm("Projektmanagement 20A-BI WiSe 2021");
        evaluationFormService.addQuestion(projektmanagement,new Question(1, "Wie war das Modul?"));
        evaluationFormService.addQuestion(projektmanagement,new Question(2, "Wie war der Dozent?"));
        evaluationFormService.addQuestion(projektmanagement,new Question(3, "Wie wurde die Vorbereitung auf die Klausur empfunden?"));
        evaluationFormService.addQuestion(projektmanagement,new Question(4, "Waren die erhaltenden Informationen klar und versändlich?"));
        evaluationFormService.addQuestion(projektmanagement,new Question(5, "Was der Umfang der Vorlesung angemessen?"));
        evaluationFormService.save(projektmanagement);

        EvaluationForm microeconomics = new EvaluationForm("Microeconomics 20A-BI WiSe 2020");
        evaluationFormService.addQuestion(microeconomics,new Question(1, "Wie war das Modul?"));
        evaluationFormService.addQuestion(microeconomics,new Question(2, "Wie war der Dozent?"));
        evaluationFormService.addQuestion(microeconomics,new Question(3, "Wie empfanden Sie den Informationsfluss"));
        evaluationFormService.addQuestion(microeconomics,new Question(4, "Konnten die persönlichen Entwicklungsziele erfüllt werden?"));
        evaluationFormService.save(microeconomics);


        EvaluationForm programmierung2 = new EvaluationForm("Programmierung 2 20A-BI WiSe 2021");

        evaluationFormService.addQuestion(programmierung2,new Question(1, "Wie war das Modul?"));
        evaluationFormService.addQuestion(programmierung2,new Question(2, "Wie war der Dozent?"));
        evaluationFormService.addQuestion(programmierung2,new Question(3, "Wie war der Arbeitsaufwand?"));
        evaluationFormService.addQuestion(programmierung2,new Question(4, "Waren die Aufgaben interessant und abwechlungsreich?"));
        evaluationFormService.save(programmierung2);

        EvaluationForm evaluationForm2 = new EvaluationForm("Rechnungswesen 20A-BI WiSe 2020");
        evaluationFormService.addQuestion(evaluationForm2,new Question(1, "Wie war das Modul?"));
        evaluationFormService.addQuestion(evaluationForm2,new Question(2, "Wie war der Dozent?"));
        evaluationFormService.addQuestion(evaluationForm2,new Question(3, "Wurden aufkommende Probleme zu Ihrer Zufriedenheit behoben?"));
        evaluationFormService.addQuestion(evaluationForm2,new Question(4, "Wie zufrieden sind Sie mit diesem Modul insgesamt?"));
        evaluationFormService.save(evaluationForm2);

        Lecture programmierungLecture = lectureService.createLecture("Projektmanagement 20A-BI WiSe 2021");
        lectureService.changeStatus(programmierungLecture);
        lectureService.addEvaluationForm(projektmanagement,programmierungLecture);
        lectureService.save(programmierungLecture);

        Lecture microenconomicsLecture = lectureService.createLecture("Microeconomics 20A-BI WiSe 2020");
        lectureService.addEvaluationForm(microeconomics,microenconomicsLecture);
        lectureService.save(microenconomicsLecture);

        Lecture projektmanagementLecture = lectureService.createLecture("Microeconomics 20A-BI WiSe 2020");
        lectureService.changeStatus(projektmanagementLecture);
        lectureService.save(projektmanagementLecture);

        Lecture rechnungswesenLecture = lectureService.createLecture("Microeconomics 20A-BI WiSe 2020");
        lectureService.changeStatus(rechnungswesenLecture);
        lectureService.save(rechnungswesenLecture);

       // ratingService.rate(evaluationFormService.findQuestionById(evaluationForm,1l),createUser("Otto","su", User.USER_ROLE),4);

        // Erstellt die vorher festgelegten Nutzer
        createUser("Test", "su", User.USER_ROLE);
        createUser("Admin", "su", User.ADMIN_ROLE);



 }
 // Erstellt neuen Nutzer in Datenbank (nur für den Admin nötig)
    private User createUser(String name, String password, String role){
        return userService.save(new User(name, passwordEncoder.encode(password), role));
    }

}
