package de.hsba.bi.demo6.web.rating;

import de.hsba.bi.demo6.rating.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingFormConverter {

//  Das rating-Objekt updaten mit Validierung
    public Rating update(Rating rating, RatingForm form){
        rating.setScore(form.getScore());
        return rating;
    }
}
