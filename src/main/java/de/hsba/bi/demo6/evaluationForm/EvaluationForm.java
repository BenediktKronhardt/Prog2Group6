package de.hsba.bi.demo6.evaluationForm;

import java.util.ArrayList;
import java.util.List;

public class EvaluationForm {
//    Hier kommen die Variablen hin
    private int id;

    private List<Question> questions;

    public List<Question> getQuestions(){
        if (questions == null){
            questions = new ArrayList<>();
        }
        return questions;
    }
//    Hier müssen noch Methoden ergänzt werden
}
