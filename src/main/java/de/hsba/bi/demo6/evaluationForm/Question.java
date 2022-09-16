package de.hsba.bi.demo6.evaluationForm;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;

//Getter, Setter und NoArgsKonstruktor automatisch von lombok erzeugen lassen
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Question {
    @Setter(AccessLevel.NONE)
    @Id
//  generator, damit die ID's nicht aufeinander aufbauen, sondern nur für Question-Objekte gezählt werden
    @GeneratedValue(generator = "Question_ID")
    private Long id;

//  invers zu EvaluationForm
    @ManyToOne(optional = false)
    private EvaluationForm evaluationForm;

//  optional=false: muss gesetzt werden
    @Basic(optional = false)
    private Integer countQuestion;

//  optional=false: muss gesetzt werden
    @Basic(optional = false)
    private String text;

//  Konstruktor mit Args
    public Question(final int countQuestion,final String text){
        this.countQuestion=countQuestion;
        this.text=text;
    }



}