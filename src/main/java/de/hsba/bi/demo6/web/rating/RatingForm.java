package de.hsba.bi.demo6.web.rating;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
// Stellt das EingabeFormular für das Rating auf der HTML-Seite rating/index.html dar
public class RatingForm {

//  Bewertung muss zwischen 1 und 5 sein
    @Min(value=1, message= "Bitte zwischen 1 bis 5 bewerten")
    @Max(value=5, message= "Bitte zwischen 1 bis 5 bewerten")
    private Integer score;
}
