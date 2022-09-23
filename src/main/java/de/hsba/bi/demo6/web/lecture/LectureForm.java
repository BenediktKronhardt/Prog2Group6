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
    @Min(2000)
    @Max(2023)
    private Integer startyear;

//  Maximal 50 Zeichen lang
    @Size.List({
            @Size(max = 50, message = "Bitte maximal 50 Zeichen eingeben")
    })
    private String course;

//  Die maximale Anzahl an Studenten in einer Lehrveranstaltung sind 150 Studenten
    @Max(150)
    private Integer studentCount;

//  Die maximale Anzahl an Kontaktstunden sind 96
    @Max(96)
    private Integer contactHours;

//  Maximal 50 Zeichen lang
    @Size.List({
            @Size(max = 50, message = "Bitte maximal 50 Zeichen eingeben")
    })
    private String lecturerName;

}
