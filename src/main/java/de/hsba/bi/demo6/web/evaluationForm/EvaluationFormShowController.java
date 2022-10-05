package de.hsba.bi.demo6.web.evaluationForm;


import de.hsba.bi.demo6.lecture.Lecture;
import de.hsba.bi.demo6.lecture.LectureService;
import de.hsba.bi.demo6.user.User;
import de.hsba.bi.demo6.user.UserService;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.Question;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormService;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/evaluationForms/{id}")
@RequiredArgsConstructor
public class EvaluationFormShowController {

    private final EvaluationFormService evaluationFormService;
    private final LectureService lectureService;
    private final EvaluationFormFormConverter formConverter;
    private final UserService userService;

    @ModelAttribute("evaluationForm")
    public EvaluationForm getEvaluationForm(@PathVariable("id")Long id){
        EvaluationForm evaluationForm = evaluationFormService.getEvaluationForm(id);
        if (evaluationForm == null){
            throw new de.hsba.bi.demo6.web.NotFoundException();
        }
        return evaluationForm;
    }


    @ModelAttribute("users")
    public List<User> getUsers() {
        return userService.findUsers();
    }


    @GetMapping
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("evaluationFormForm", formConverter.toForm(getEvaluationForm(id)));
        model.addAttribute("questionForm", new QuestionForm());
        model.addAttribute("lecture",lectureService.getAll());
        return "evaluationForms/showEvaluationForm";
    }

//  Hier kann ein Lecture-Objekt, welches bisher noch keinem Evaluationsbogen zugeordnet ist, einem Evaluationsbogen zugeordnet werden.
//  Zuerst wird das EvaluationForm-Objekt über die Id in showEvaluationForm definiert, dann das Lecture-Objekt über den Value im Form-Objekt definiert und dieses zu dem EvaluationForm-Objekt hinzugefügt
    @PostMapping(path = "/addLecture")
    public String addLectureToEvaluationForm(@PathVariable("id") Long id, Long lectureId){
        EvaluationForm evaluationForm = evaluationFormService.getEvaluationForm(id);
        Lecture lecture = lectureService.getLecture(lectureId);
        lectureService.addEvaluationForm(evaluationForm, lecture);
        return "redirect:/evaluationForms/" +id;
    }

//  Den Namen eines Evaluationsbogens ändern
    @PostMapping
    public String change(Model model, @PathVariable("id") Long id, @ModelAttribute("evaluationFormForm") @Valid EvaluationFormForm evaluationFormForm, BindingResult evaluationFormBinding){
       //Wenn der neue Name leer ist, kann der Name nicht geändert werden
        if (evaluationFormBinding.hasErrors()){
           model.addAttribute("questionForm", new QuestionForm());
           return "evaluationForms/showEvaluationForm";
       }
        EvaluationForm evaluationForm = getEvaluationForm(id);
        evaluationFormService.save(formConverter.update(evaluationForm, evaluationFormForm));
        return "redirect:/evaluationForms/" +id;
    }

//  Den Namen eines Question-Objektes löschen
    @PostMapping(path="/changeQuestion/{questionId}")
    public String changeQuestion(Model model, @PathVariable("id") Long id, @PathVariable("questionId") Long questionId, @ModelAttribute("questionForm") @Valid QuestionForm questionForm, BindingResult questionFormBinding){
        EvaluationForm evaluationForm = getEvaluationForm(id);
        //Wenn der neue Name leer ist oder kein Fragezeichen am Ende enthält, kann der Name nicht geändert werden
        if (questionFormBinding.hasErrors()){
            model.addAttribute("evaluationFormForm", formConverter.toForm(evaluationForm));
            return "evaluationForms/showEvaluationForm";
        }
//      Ein neues Question-Objekt wird erstellt, indem es mit der entsprechenden Id gesucht wird
        Question question = evaluationFormService.findQuestionById(evaluationForm, questionId);
//      Das erstellte Question-Objekt wird geupdatet (Der Name)
        formConverter.update(question, questionForm);
//      Das Question-Objekt wird geändert
        evaluationFormService.changeQuestion(evaluationForm, question);
        return "redirect:/evaluationForms/" +id;
    }

// Eine Frage lästt sich zum EvaluationsBogen hinzufügen
    @PostMapping(path = "/questions")
    public String addQuestion(Model model, @PathVariable("id") Long id, @ModelAttribute("questionForm") @Valid QuestionForm questionForm, BindingResult questionFormBinding){
        EvaluationForm evaluationForm = getEvaluationForm(id);
        // Wenn das Question Objekt einen Error wirft (keinen Inhalt), dann wird keine Frage hinzugefügt
        if (questionFormBinding.hasErrors()){
            model.addAttribute("evaluationFormForm", formConverter.toForm(evaluationForm));
            return "evaluationForms/showEvaluationForm";
        }
        evaluationFormService.addQuestion(evaluationForm, formConverter.update(new Question(), questionForm));
        return "redirect:/evaluationForms/{id}";
    }

//  Ein EvaluationForm-Objekt löschen, danach erfolgt eine Weiterleitung zur index-Seite
    @PostMapping(path="/delete")
    public String delete(@PathVariable("id") Long id){
        evaluationFormService.delete(id);
        return "redirect:/evaluationForms/";
    }

//  Ein Question-Objekt löschen, danach auf der evaluationForm.show Seite bleiben. In der URL wird am Ende noch die Id des Question-Objektes mitgegeben
    @PostMapping(path="/deleteQuestion/{questionId}")
    public String deleteQuestion(@PathVariable("id") Long id, @PathVariable("questionId") int questionId){
        EvaluationForm evaluationform = getEvaluationForm(id);
        evaluationFormService.deleteQuestion(evaluationform,questionId);
        return "redirect:/evaluationForms/"+id;
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFound(){
        return "/notFound/";
    }
}