package de.hsba.bi.demo6.web.evaluationForm;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class QuestionForm {

// Es müssen mindestens 3 Zeichen eingegeben werden um den Namen des EF zu ändern (max 255 Zeichen)
    @Size.List({
            @Size(min = 3, message = "Bitte mindestens 3 Zeichen eingeben"),
            @Size(max = 255, message = "Der Name darf nicht länger als 255 Zeichen sein")
    })
// Stellt sicher, dass die Frage nur mit einem "?" enden kann. Es werden alle anderen "Chars" im Vorhinein zugelassen (".*") 
    @Pattern(regexp = ".*\\b[?]", message = "Bitte die Frage mit einem Fragezeichen beenden")
    private String name;
}
