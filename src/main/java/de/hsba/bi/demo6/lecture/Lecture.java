package de.hsba.bi.demo6.lecture;

import de.hsba.bi.demo6.evaluationForm.EvaluationForm;

import javax.persistence.*;

@Entity
@Table(name="Lecture")
public class Lecture {

    @Id
    @GeneratedValue(generator = "Lecture_id")
    private Long id;

    @Basic
    private String name;

    @Basic
    private Integer startyear;

    @Basic
    private String course;

    @OneToOne
    @JoinColumn(name="evaluationForm_id", referencedColumnName = "id")
    private EvaluationForm evaluationForm;
}
