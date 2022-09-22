package de.hsba.bi.demo6.web.lecture;

import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import lombok.Getter;
import lombok.Setter;

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

    private Integer startyear;

    private String course;

    private Integer studentCount;

    private Integer contactHours;

    private String lecturerName;

}
