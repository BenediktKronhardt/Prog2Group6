package de.hsba.bi.demo6.rating;

import de.hsba.bi.demo6.evaluationForm.Question;
import de.hsba.bi.demo6.user.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class RatingService {

    private final RatingRepository repository;

//  Service-Klasse greift auf Repository zu
    public RatingService(RatingRepository repository){
        this.repository=repository;
    }

//  Alle Ratings anzeigen lassen
    public Collection<Rating> getAll() {
        return repository.findAll();
    }

    public Rating save(Rating rating){
        return repository.save(rating);
    }

    public Rating rate(Question question, User user, Integer score){
        Rating rating = new Rating();
        rating.setQuestion(question);
        rating.setUser(user);
        rating.setScore(score);
        return repository.save(rating);
    }

}
