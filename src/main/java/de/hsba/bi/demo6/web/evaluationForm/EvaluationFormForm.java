package de.hsba.bi.demo6.web.evaluationForm;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter

public class EvaluationFormForm {

// Es müssen mindestens 3 Zeichen eingegeben werden um den Namen des EF zu ändern (max 255 Zeichen)
  //  @NotBlank(message = "Bitte geben Sie einen Namen ein")
    @Size.List({
            @Size(min = 3, message = "Bitte mindestens 3 Zeichen eingeben"),
            @Size(max = 255, message = "Der Name darf nicht länger als 255 Zeichen sein")
    })
    private String name;
}
