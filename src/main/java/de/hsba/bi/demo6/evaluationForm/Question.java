package de.hsba.bi.demo6.evaluationForm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Getter, Setter und Konstruktoren automatisch von lombok erzeugen lassen
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    //  Attribute der Klasse "Question"
    private int id;
    private String text;

}