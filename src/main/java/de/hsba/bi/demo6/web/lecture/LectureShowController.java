package de.hsba.bi.demo6.web.lecture;


import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormService;
import de.hsba.bi.demo6.lecture.Lecture;
import de.hsba.bi.demo6.lecture.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureShowController {

    private final LectureService lectureService;
    private final LectureFormConverter formConverter;
    private final EvaluationFormService evaluationFormService;

    // Wenn Lecture vorhanden ist, wird dieses zurückgegeben, ansonsten wird eine NotFound exeption geworfen
    @ModelAttribute("lecture")
    public Lecture getLecture(@PathVariable("id")Long id){
        Lecture lecture = lectureService.getLecture(id);
        if (lecture == null){
            throw new de.hsba.bi.demo6.web.NotFoundException();
        }
        return lecture;
    }

    // Zeigt das Lecture
    @GetMapping(path = "/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("lectureForm", formConverter.toForm(getLecture(id)));
        model.addAttribute("evaluationForm", evaluationFormService.getAll());
        return "lectures/showLecture";
    }

    //  Hier kann ein EvaluationForm-Objekt, welches bisher noch keiner Lehrveranstaltung zugeordnet ist, einer Lehrveranstaltung zugeordnet werden.
    //  Zuerst wird das Lecture-Objekt über die Id in showLecture definiert, dann das EvaluationForm-Objekt über den Value im Form-Objekt definiert und dieses zu dem lecture-Objekt hinzugefügt
    @PostMapping(path = "/{id}")
    public String addEvaluationFormToLecture(@PathVariable("id") Long id, Long evaluationFormId){
        Lecture lecture = lectureService.getLecture(id);
        EvaluationForm evaluationForm = evaluationFormService.getEvaluationForm(evaluationFormId);
        lectureService.addEvaluationForm(evaluationForm, lecture);
        return "redirect:/lectures/" +id;
    }

}
