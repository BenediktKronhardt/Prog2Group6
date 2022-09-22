package de.hsba.bi.demo6.web.lecture;


import de.hsba.bi.demo6.lecture.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureIndexController {

    private final LectureService lectureService;


    @GetMapping
    public String index(){
        return "lectures/index";
    }


    // Ein neues lecture-Objekt anlegen. Danach auf der gleichen Seite bleiben
    @PostMapping
    public String create(String lecture_name, Integer startyear, String course, Integer countStudents, Integer contacthours, String teacher){
        lectureService.createLecture(lecture_name, startyear,course,countStudents,contacthours,teacher);
        return "redirect:/lectures/";
    }
}
