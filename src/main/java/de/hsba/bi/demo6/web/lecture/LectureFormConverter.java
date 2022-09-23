package de.hsba.bi.demo6.web.lecture;

import de.hsba.bi.demo6.lecture.Lecture;
import org.springframework.stereotype.Component;

@Component
public class LectureFormConverter {


// Setzt alle Attribute gemeinsam
    LectureForm toFormFull(Lecture lecture){
        LectureForm form = new LectureForm();
        form.setName(lecture.getName());
        form.setCourse(lecture.getCourse());
        form.setContactHours(lecture.getContactHours());
        form.setStartyear(lecture.getStartyear());
        form.setLecturerName(lecture.getLecturerName());
        form.setStudentCount(lecture.getStudentCount());
        return form;
    }

    Lecture updateFull(Lecture lecture, LectureForm form){
        lecture.setName(form.getName());
        lecture.setCourse(form.getCourse());
        lecture.setContactHours(form.getContactHours());
        lecture.setStartyear(form.getStartyear());
        lecture.setLecturerName(form.getLecturerName());
        lecture.setStudentCount(form.getStudentCount());
        return lecture;
    }
}
