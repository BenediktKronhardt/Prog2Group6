package de.hsba.bi.demo6.lecture;

import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LectureService {

    private final LectureRepository repository;

    //  Service-Klasse greift auf Repository zu
    public LectureService(LectureRepository repository){
        this.repository = repository;
    }


    //  Neues Lecture Objekt erstellen und im Repository speichern
    public Lecture createLecture(String name, Integer startyear, String course) {
        Lecture lecture = new Lecture();
        lecture.setName(name);
        lecture.setStartyear(startyear);
        lecture.setCourse(course);
        return repository.save(lecture);
    }



}
