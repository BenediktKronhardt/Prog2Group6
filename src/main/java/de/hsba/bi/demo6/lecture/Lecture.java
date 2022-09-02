package de.hsba.bi.demo6.lecture;

import de.hsba.bi.demo6.evaluationForm.EvaluationForm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="Lecture")
@NoArgsConstructor
public class Lecture {
    @Getter
    @Id
    @GeneratedValue(generator = "Lecture_id")
    private Long id;

    @Basic
    @Getter
    @Setter
    private String name;

// Erstellt das Int start year

    @Basic
    @Getter
    @Setter
    private Integer startyear;

    @Basic
    @Getter
    @Setter
    private String course;

    @Basic
    @Getter
    @Setter
    private Integer studentCount;

    @Basic
    @Getter
    @Setter
    private Integer contactHours;

    @Basic
    @Getter
    @Setter
    private String lecturerName;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="evaluationForm_id", referencedColumnName = "id")
    private EvaluationForm evaluationForm;

    @Override
    public String toString(){
        return "Name: "+name+", Studienfach: "+course+", Jahrgang: "+startyear+", Kontaktstunden: "+contactHours+", Studententenanzahl: "+studentCount+", Dozent: "+lecturerName;
    }



}
