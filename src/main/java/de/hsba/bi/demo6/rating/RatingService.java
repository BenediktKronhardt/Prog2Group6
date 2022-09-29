package de.hsba.bi.demo6.rating;

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

}
