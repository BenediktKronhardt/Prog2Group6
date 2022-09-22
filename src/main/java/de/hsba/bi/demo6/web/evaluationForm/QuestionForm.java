package de.hsba.bi.demo6.web.evaluationForm;


import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter

public class QuestionForm {

    private Long id;

    private EvaluationForm evaluationForm;

    private Integer countQuestion;

// Der Text in der Frage darf im "QuestionForm" nicht leer sein.
    @NotBlank(message = "Bitte eine Frage eingeben")
    private String text;

}
