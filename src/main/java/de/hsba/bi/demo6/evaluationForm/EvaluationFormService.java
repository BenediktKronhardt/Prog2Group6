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
    public void addQuestion(EvaluationForm evaluationForm, Question question) {
        evaluationForm.getQuestions().add(question);
    }

//  Alle Evaluationsbögen anzeigen lassen
    public Collection<EvaluationForm> getAll() {
        return repository.findAll();
    }
}
