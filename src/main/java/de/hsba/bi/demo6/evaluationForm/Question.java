package de.hsba.bi.demo6.evaluationForm;

public class Question {
//  Attribute der Klasse "Question"
    private int id;
    private String text;

//  Konstruktor -> es werden immer eine id und ein String (text) benötigt
    public Question(int id, String text){
        this.id=id;
        this.text=text;
    };

//  Methoden - Getter und Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
