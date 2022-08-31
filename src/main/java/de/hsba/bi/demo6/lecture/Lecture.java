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

    @Id
    @GeneratedValue(generator = "Lecture_id")
    private Long id;

    @Basic
    @Getter
    @Setter
    private String name;

    @Basic
    @Getter
    @Setter
    private Integer startyear;

    @Basic
    @Getter
    @Setter
    private String course;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="evaluationForm_id", referencedColumnName = "id")
    private EvaluationForm evaluationForm;

    @Override
    public String toString(){
        return name;
    }

}
