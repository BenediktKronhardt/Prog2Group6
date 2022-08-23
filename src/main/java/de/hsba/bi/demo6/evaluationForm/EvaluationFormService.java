package de.hsba.bi.demo6.evaluationForm;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

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
    public void addQuestion(EvaluationForm evaluationForm, Question question) {
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

        evaluationForm.getQuestions().add(question);

//      Hier wird i als ID gesetzt
        question.setId(i);

    }

//  Alle Evaluationsbögen anzeigen lassen
    public Collection<EvaluationForm> getAll() {
        return repository.findAll();
    }

//  Evaluationsbogen mit bestimmter id löschen
    public void delete(Integer id){
        repository.deleteById(id);
    }


}

