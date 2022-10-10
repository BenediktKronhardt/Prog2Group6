package de.hsba.bi.demo6.web.lecture;


import de.hsba.bi.demo6.lecture.Lecture;
import de.hsba.bi.demo6.lecture.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureIndexController {

    private final LectureService lectureService;
    private final LectureFormConverter formConverter;

    @GetMapping
    public String index(Model model){
        model.addAttribute("lectureForm", new LectureForm());
        return "lectures/index";
    }

    // Ein neues lecture-Objekt anlegen. Danach auf der gleichen Seite bleiben
    @PostMapping
    public String create(@ModelAttribute("lectureForm") @Valid LectureForm lectureForm, BindingResult lectureBinding){
        //Wenn Fehler vorhanden sind, dann passiert nichts (Validierung). Man bleibt auf gleicher Seite ohne Meldung
        if (lectureBinding.hasErrors()){
            return "lectures/index";
        }
        Lecture lecture = lectureService.save(formConverter.update(new Lecture(),lectureForm));
        return "redirect:/lectures/" + lecture.getId();
    }

//  Im unteren ist die Methode, welche aufgerufen wird, wenn der Button "Attribute ändern" auf der lectureShow Seite aufgerufen wird.
//  Hier werden die Attribute "lecture" und "lectureForm" mitgegeben
    @GetMapping(path="{id}/change")
    public String changeGet(Model model, @PathVariable("id") long lectureId){
        model.addAttribute("lecture", lectureService.getLecture(lectureId));
        model.addAttribute("lectureForm",formConverter.toForm(lectureService.getLecture(lectureId)));
        return "lectures/index";
    }

//  Methode wird aufgerufen, wenn auf Button "ändern" in lectures/index geklickt wird
    @PostMapping(path="{id}/change")
    public String changePost(Model model, @PathVariable("id") long lectureId, @ModelAttribute("lectureForm") @Valid LectureForm lectureForm, BindingResult lectureBinding){
//      Hier wird das "lecture" hinzugefügt, um zwischen "anlegen" und "ändern" zu unterscheiden
        model.addAttribute("lecture", lectureService.getLecture(lectureId));
//      Wenn Fehler vorhanden sind, dann passiert nichts (Validierung). Man bleibt auf gleicher Seite
        if (lectureBinding.hasErrors()){
            return "lectures/index";
        }
//      Das neu geänderte Lecture-Objekt wird in der Datenbank gespeichert
        lectureService.save(formConverter.update(lectureService.getLecture(lectureId), lectureForm));
//      Bei erfolgreicher Änderung erfolgt eine Weiterleitung auf die showLecture-Seite des geänderten Lectures
        return "redirect:/lectures/" +lectureId;
    }
}
