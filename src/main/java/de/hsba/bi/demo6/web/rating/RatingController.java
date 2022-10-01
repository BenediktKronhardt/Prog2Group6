//TODO: Umbau der Anwendung mit RatingForm, ...
package de.hsba.bi.demo6.web.rating;

import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormService;
import de.hsba.bi.demo6.evaluationForm.Question;
import de.hsba.bi.demo6.rating.RatingService;
import de.hsba.bi.demo6.user.User;
import de.hsba.bi.demo6.user.UserAdapterService;
import de.hsba.bi.demo6.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private final UserService userService;

//  ratings und der Evaluationsbogen werden als Modelle hinzugefügt
    @GetMapping
    public String index(@PathVariable("id") Long id, Model model){
        model.addAttribute("ratings", ratingService.getAll());
        model.addAttribute("evaluationForm", getEvaluationForm(id));

    return "ratings/index";
    }

//  Füge ein Rating hinzu. Momentan gibt es für jede Frage einen einzelnen Button zum bewerten, daher muss die Id der Frage in der URL mitgegeben werden. Auch wird der jeweils eingeloggte User mitgegeben, welcher danach mit der Methode getUserByUsername bestimmt wird.
    @PostMapping(path = "/{questionId}/{userName}")
    public String rate(@PathVariable("id") Long id, Integer score,@PathVariable("questionId") Long questionId, @PathVariable("userName") String userName){
        EvaluationForm evaluationForm = getEvaluationForm(id);
        User user = userService.findUserByName(userName);
        ratingService.rate(evaluationFormService.findQuestionById(evaluationForm, questionId),user,score);
        return "redirect:/evaluationForms/"+id+"/ratings";
    }

}
