package de.hsba.bi.demo6.evaluationForm;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class EvaluationForm {

//  id zur eindeutigen Identifikation
    @Getter
    @Setter
    private int id;

//  Name des Fragebogens
    @Getter
    @Setter
    private String name;

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
