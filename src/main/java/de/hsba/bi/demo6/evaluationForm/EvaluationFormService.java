package de.hsba.bi.demo6.evaluationForm;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EvaluationFormService {

    private final EvaluationFormRepository repository;

//  Service-Klasse greift auf Repository zu
    public EvaluationFormService(EvaluationFormRepository repository){
        this.repository = repository;
    }

//  Neues EvaluationForm Objekt erstellen und im repository speichern
    public EvaluationForm createEvaluationForm(String name) {
        EvaluationForm evaluationForm = new EvaluationForm();
        evaluationForm.setName(name);
        return repository.save(evaluationForm);
    }

//  EvaluationForm-Objekt per ID in Repository finden
    public EvaluationForm getEvaluationForm(int id) {
        return repository.findById(id).orElse(null);
    }

//  Frage zu EvaluationForm hinzufügen
    public EvaluationForm addQuestion(EvaluationForm evaluationForm, Question question) {
//   Variable i zum Bestimmen der Id der Question
     int i=0;

//     Wenn es noch keine Questions gibt, wird i=1 gesetzt
        if(evaluationForm.getQuestions().isEmpty()){
            i=1;
        }

//      Ansonsten ist i um den Wert 1 größer als die Anzahl der Questions
        else{
            i=evaluationForm.getQuestions().size()+1;
        }


//      Hier wird i als ID gesetzt
        question.setId(i);

//      Damit nicht die ID als Wert genommen wird, wird "i" zu "j"
        int j = 0;
        j=i;

//      Es kann eine maximale Grenze erstellt werden
        if (j <= 4){

            evaluationForm.getQuestions().add(question);
        }

//      Gibt den Bogen zurück damit er ausgelesen werden kann
        return evaluationForm;
    }

//  Alle Evaluationsbögen anzeigen lassen
    public Collection<EvaluationForm> getAll() {
        return repository.findAll();
    }


}

