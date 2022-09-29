package de.hsba.bi.demo6.rating;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RatingService {

    private final RatingRepository repository;

//  Service-Klasse greift auf Repository zu
    public RatingService(RatingRepository repository){
        this.repository=repository;
    }
}
