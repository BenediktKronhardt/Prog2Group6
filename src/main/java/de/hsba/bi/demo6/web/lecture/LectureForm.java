package de.hsba.bi.demo6.web.lecture;

import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Stellt das EingabeFormular auf der HTML dar
public class LectureForm {


    private String name;

    private Integer startyear;

    private String course;

    private Integer studentCount;

    private Integer contactHours;

    private String lecturerName;

}
