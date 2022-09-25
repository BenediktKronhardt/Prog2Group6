package de.hsba.bi.demo6.evaluationForm;


import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class EvaluationFormService {

    private final EvaluationFormRepository repository;

//  Service-Klasse greift auf Repository zu
    public EvaluationFormService(EvaluationFormRepository repository){
        this.repository = repository;
    }

//  Evaluationsbogen abspeichern
    public EvaluationForm save(EvaluationForm evaluationForm) {
        return repository.save(evaluationForm);
    }

//  EvaluationForm-Objekt per ID in Repository finden
    public EvaluationForm getEvaluationForm(long id) {
        return repository.findById(id).orElse(null);
    }

//  Frage zu EvaluationForm hinzufügen
    public void addQuestion(EvaluationForm evaluationForm, Question question) {
//  Maximale Anzahl an Fragen die gestellt werden können
        if (evaluationForm.getQuestions().size() < 10) {
//     Wenn es noch keine Questions gibt, wird countQuestion=1 gesetzt
            if (evaluationForm.getQuestions().isEmpty()) {
                question.setCountQuestion(1);
            }
//      Ansonsten ist countQuestion um den Wert 1 größer als die Anzahl der Questions
            else {
                question.setCountQuestion(evaluationForm.getQuestions().size() + 1);
            }
//      Der Evaluationsbogen wird zu der Frage gesetzt und die Frage wird hinzugefügt
        question.setEvaluationForm(evaluationForm);
        evaluationForm.getQuestions().add(question);
        }
    }

//  Alle Evaluationsbögen anzeigen lassen
    public Collection<EvaluationForm> getAll() {
        return repository.findAll();
    }

//  Evaluationsbogen mit bestimmter id löschen
    public void delete(Long id){
        repository.deleteById(id);
    }

//  Eine Frage löschen
    public void deleteQuestion(EvaluationForm evaluationForm, int questionId){

//      Durch jedes Question-Element in der ArrayList wird durchiteriert. Wenn die Id mit der mitgegebenen questionId übereinstimmt, wird das entsprechende Element aus der ArrayList gelöscht
        for (int i=0; i<evaluationForm.getQuestions().size(); i++){
              if (evaluationForm.getQuestions().get(i).getId()==questionId){
                evaluationForm.getQuestions().remove(i);
                }
            }
    }

    }






