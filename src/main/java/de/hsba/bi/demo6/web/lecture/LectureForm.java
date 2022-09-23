package de.hsba.bi.demo6.web.lecture;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


@Getter
@Setter
// Stellt das EingabeFormular auf der HTML dar
public class LectureForm {

    @Size.List({
            @Size(min = 3, message = "Bitte mindestens 3 Zeichen eingeben"),
            @Size(max = 50, message = "Bitte maximal 50 Zeichen eingeben")
    })
    private String name;

//  Der Jahrgang muss zwischen 2000 und 2023 liegen
    @Min(value = 2000, message = "Jahrgang muss zwischen 2000-2023 liegen")
    @Max(value= 2023, message = "Jahrgang muss zwischen 2000-2023 liegen")
    private Integer startyear;

//  Maximal 50 Zeichen lang
    @Size.List({
            @Size(max = 50, message = "Bitte maximal 50 Zeichen bei Studienfach eingeben")
    })
    private String course;

//  Die maximale Anzahl an Studenten in einer Lehrveranstaltung sind 150 Studenten
    @Max(value= 150, message = "Es können maximal 150 Studenten an einer Lehrveranstaltung teilnehmen")
    private Integer studentCount;

//  Die maximale Anzahl an Kontaktstunden sind 96
    @Max(value=96, message = "Es können maximal 96 Kontaktstunden ausgewählt werden")
    private Integer contactHours;

//  Maximal 50 Zeichen lang
    @Size.List({
            @Size(max = 50, message = "Bitte maximal 50 Zeichen bei Dozentname eingeben")
    })
    private String lecturerName;

}
