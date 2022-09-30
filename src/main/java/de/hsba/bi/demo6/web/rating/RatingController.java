package de.hsba.bi.demo6.web.rating;

import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormService;
import de.hsba.bi.demo6.rating.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/evaluationForms/{id}/ratings")
@RequiredArgsConstructor
public class RatingController {

//  getEvaluationForm-Methode, da die Fragen, welche angezeigt werden sollen, nur über einen Evaluationsbogen angezeigt werden können. Dafür muss dieser erst "geholt" werden. Die Id in der URL ist die Id von dem Evaluationsbogen.
//  Wenn Evaluationsbogen nicht gefunden wird, wird eine NotFoundException geworfen.
    @ModelAttribute("evaluationForm")
    public EvaluationForm getEvaluationForm(@PathVariable("id")Long id){
        EvaluationForm evaluationForm = evaluationFormService.getEvaluationForm(id);
        if (evaluationForm == null){
            throw new de.hsba.bi.demo6.web.NotFoundException();
        }
        return evaluationForm;
    }

    private final RatingService ratingService;
    private final EvaluationFormService evaluationFormService;

//  ratings und der Evaluationsbogen werden als Modelle hinzugefügt
    @GetMapping
    public String index(@PathVariable("id") Long id, Model model){
        model.addAttribute("ratings", ratingService.getAll());
        model.addAttribute("evaluationForm", getEvaluationForm(id));
    return "ratings/index";
    }
}
