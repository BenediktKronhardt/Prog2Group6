package de.hsba.bi.demo6.rating;

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


   // private Question question;

   // private User user;

}
