package de.hsba.bi.demo6.lecture;

import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class LectureService {

    private final LectureRepository repository;

//  Service-Klasse greift auf Repository zu
    public LectureService(LectureRepository repository){
        this.repository = repository;
    }

//  Neues Lecture-Objekt mit allen Attributen erstellen und im Repository speichern
    public Lecture createLecture(String name, Integer startyear, String course, Integer studentCount, Integer contactHours, String lecturerName) {
        Lecture lecture = new Lecture();
        lecture.setName(name);
        lecture.setStartyear(startyear);
        lecture.setCourse(course);
        lecture.setStudentCount(studentCount);
        lecture.setContactHours(contactHours);
        lecture.setLecturerName(lecturerName);
//        lecture.setStatus(status);
        return repository.save(lecture);
    }

//  Neues Lecture-Objekt kann auch nur mit einem Namen angelegt und im Repository gespeichert werden
    public Lecture createLecture(String name) {
        Lecture lecture = new Lecture();
        lecture.setName(name);
        return repository.save(lecture);
    }

//  Alle Lectures anzeigen lassen
    public Collection<Lecture> getAll() {
        return repository.findAll();
    }

//  Speichert die lectures 
    public Lecture save(Lecture lecture) {
        return repository.save(lecture);
    }

//  Lecture nach Id suchen
    public Lecture getLecture(long id) {
        return repository.findById(id).orElse(null);
    }

//  Evaluationsbogen zu lecture hinzufügen
    public void addEvaluationForm(EvaluationForm evaluationForm, Lecture lecture){
        lecture.setEvaluationForm(evaluationForm);
    }

//  Lehrveranstaltung mit bestimmter id löschen
    public void delete(Long id){
        repository.deleteById(id);
    }



}
