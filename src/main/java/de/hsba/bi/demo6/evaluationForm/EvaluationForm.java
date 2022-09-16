package de.hsba.bi.demo6.evaluationForm;

import de.hsba.bi.demo6.lecture.Lecture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name="EvaluationForm")
public class EvaluationForm {

//  Id zur eindeutigen Identifikation
    @Getter
    @Id
//  generator, damit die ID's nicht aufeinander aufbauen, sondern nur für EvaluationForm-Objekte gezählt werden
    @GeneratedValue(generator = "EvaluationForm_ID")
    private Long id;

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

    public EvaluationForm(final String name){
        this.name = name;
    }

// Beim speichern wird automatisch auch lecture gespeichert, wenn ein Evaluationsbogen gelöscht wird, wird nicht automatisch auch das Fach gelöscht
// EvaluationForm ist der Eigentümer, damit nur eine Assoziation entsteht
   @Getter
   @Setter
    @OneToOne(orphanRemoval = true, optional=true, mappedBy = "evaluationForm")
    private Lecture lecture;

//  Methode "getQuestions" zur Ausgabe der Fragen, die einem EvaluationForm hinzugefügt wurden - falls keine hinzugefügt wurde, wird eine leere ArrayList erstellt
    public List<Question> getQuestions(){
        if (questions == null){
            questions = new ArrayList<>();
        }
        return questions;
    }

}
