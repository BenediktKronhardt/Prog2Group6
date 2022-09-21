package de.hsba.bi.demo6.web.evaluationForm;


import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.Question;
import de.hsba.bi.demo6.lecture.Lecture;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class EvaluationFormEntryForm {

    private Long id;

    private EvaluationForm evaluationForm;

    private Integer countQuestion;

    private String text;

}
