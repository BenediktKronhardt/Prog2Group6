package de.hsba.bi.demo6.lecture;

import org.springframework.data.jpa.repository.JpaRepository;

interface LectureRepository extends JpaRepository<Lecture, Long> {}
