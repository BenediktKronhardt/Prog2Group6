package de.hsba.bi.demo6.rating;

import de.hsba.bi.demo6.evaluationForm.Question;
import de.hsba.bi.demo6.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name="Rating")
public class Rating {
    @Getter
    @Id
    @GeneratedValue(generator = "Rating_id")
    private long id;

//  Die Antwort der Studenten
    @Basic
    @Getter
    @Setter
    private int score;

//  invers zu Question
    @Getter
    @Setter
    @ManyToOne
    private Question question;

//  invers zu User
    @ManyToOne
    @Getter
    @Setter
    private User user;

}
