package de.hsba.bi.demo6.lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByEntryDescription(@Param("search") String search);
}
