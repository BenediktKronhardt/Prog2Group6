package de.hsba.bi.demo6.evaluationForm;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EvaluationForm {

//  Id zur eindeutigen Identifikation
    @Getter
    @Id
    @GeneratedValue
    private long id;

//  Name des Fragebogens
    @Getter
    @Setter
    private String name;

//  Liste von Question-Objekten
//  cascade: Wenn ein neuer Evaluationsbogen gespeichert wird, werden auch die zugehörigen Fragen gespeichert. Wenn ein Evaluationsbogen gelöscht wird, werden auch die dazugehörigen Fragen gelöscht.
//  orphanRemoval: Es werden alle Elemente einer Frage gelöscht, die dazu gehört
//  MappedBy: EvaluationsbogenObjekt ist Eigentümer, Question ist inverse Seite
    @OneToMany(cascade= CascadeType.ALL, orphanRemoval = true, mappedBy = "evaluationForm")
    @OrderBy
    private List<Question> questions;

//  Methode "getQuestions" zur Ausgabe der Fragen, die einem EvaluationForm hinzugefügt wurden - falls keine hinzugefügt wurde, wird eine leere ArrayList erstellt
    public List<Question> getQuestions(){
        if (questions == null){
            questions = new ArrayList<>();
        }
        return questions;
    }
}
