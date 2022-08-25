package de.hsba.bi.demo6.evaluationForm;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
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

//  evaluationsbogen abspeichern
    public EvaluationForm save(EvaluationForm evaluationForm) {
        return repository.save(evaluationForm);
    }

//  EvaluationForm-Objekt per ID in Repository finden
    public EvaluationForm getEvaluationForm(long id) {
        return repository.findById(id).orElse(null);
    }

//  Frage zu EvaluationForm hinzufügen
    public void addQuestion(EvaluationForm evaluationForm, Question question) {

//   Variable i zum Bestimmen des Atrributes countQuestion der Question
     int i=0;


//  Maximale Anzahl an Fragen die gestellt werden können
        if (evaluationForm.getQuestions().size() < 10) {


//     Wenn es noch keine Questions gibt, wird i=1 gesetzt
            if (evaluationForm.getQuestions().isEmpty()) {
                i = 1;
            }

//      Ansonsten ist i um den Wert 1 größer als die Anzahl der Questions
            else {
                i = evaluationForm.getQuestions().size() + 1;
            }


//      Hier wird i als countQuestion gesetzt
        question.setEvaluationForm(evaluationForm);
        question.setCountQuestion(i);

            evaluationForm.getQuestions().add(question);
        }

    }
//  Alle Evaluationsbögen anzeigen lassen
    public Collection<EvaluationForm> getAll() {
            return repository.findAll();
        }

}

