package de.hsba.bi.demo6.evaluationForm;

import java.util.ArrayList;
import java.util.List;

public class EvaluationForm {
//  Attribute von EvaluationForm
    private int id;
//  Liste von Questions
    private List<Question> questions;

//  Methode "getQuestions" zur Ausgabe der Fragen, die einem EvaluationForm hinzugefügt wurden - falls keine hinzugefügt wurde, wird eine leere ArrayList erstellt
    public List<Question> getQuestions(){
        if (questions == null){
            questions = new ArrayList<>();
        }
        return questions;
    }
}
