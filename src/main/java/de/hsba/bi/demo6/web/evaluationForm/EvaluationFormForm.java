package de.hsba.bi.demo6.web.evaluationForm;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter

public class EvaluationFormForm {

    @NotBlank(message = "Bitte geben Sie einen Namen ein")
    private String name;
}
