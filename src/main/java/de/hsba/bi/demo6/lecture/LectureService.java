package de.hsba.bi.demo6.lecture;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Collection;


@Service
@Transactional
public class LectureService {

    private final LectureRepository repository;

//  Service-Klasse greift auf Repository zu
    public LectureService(LectureRepository repository){
        this.repository = repository;
    }

//  Neues Lecture-Objekt mit allen Attributen erstellen und im Repository speichern
    public Lecture createLecture(String name, Integer startyear, String course) {
        Lecture lecture = new Lecture();
        lecture.setName(name);
        lecture.setStartyear(startyear);
        lecture.setCourse(course);
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

    public Lecture save(Lecture lecture) {
        return repository.save(lecture);
    }

}
