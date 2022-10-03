package de.hsba.bi.demo6.evaluationForm;

import de.hsba.bi.demo6.rating.Rating;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

//  Beim Speichern soll die assoziierte Einheit ebenfalls gespeichert werden
//  Wenn eine Frage gelöscht wird, sollen nicht die Ratings gelöscht werden
//  MappedBy: Question-Objekt ist Eigentümer, Rating ist inverse Seite
    @OneToMany (cascade = CascadeType.PERSIST, orphanRemoval = false, mappedBy = "question")
    @OrderBy
    private List<Rating> ratings;

//  Konstruktor mit Args
    public Question(final int countQuestion,final String text){
        this.countQuestion=countQuestion;
        this.text=text;
    }

//  Methode "getRatings" zur Ausgabe der Bewertungen, die einer Frage hinzugefügt wurden - falls kein Rating hinzugefügt wurde, wird eine leere ArrayList erstellt
    public List<Rating> getRatings(){
        if (ratings == null){
            ratings = new ArrayList<>();
        }
        return ratings;
    }


}