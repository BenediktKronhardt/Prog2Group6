package de.hsba.bi.demo6.lecture;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import de.hsba.bi.demo6.evaluationForm.Question;
import de.hsba.bi.demo6.user.User;
import de.hsba.bi.demo6.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
@Transactional
public class LectureServiceIntegrationTest {

    private final User testUser = new User("test")

    @Autowired
    private LectureService serviceToTest;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        lectureRepository.deleteAll();
        userService.save(testUser);
    }

    @Test
    void shouldFindLectures() {
        Lecture l1 = buildLectureWithDescription("Lecture1");
        Lecture l2 = buildLectureWithDescription("Lecture2");

        List<Lecture> lectures = serviceToTest.findLectures("");
        assertThat(lectures).containsExactlyInAnyOrder(l1, l2);

        lectures = serviceToTest.findLectures("Lecture0");
        assertThat(lectures).isEmpty();

        lectures = serviceToTest.findLectures("2");
        assertThat(lectures).containsExactlyInAnyOrder(l1, l2);

        lectures = serviceToTest.findLectures("1");
        assertThat(lectures).containsExactly("l1");
    }

    private Lecture buildLectureWithDescription(String description) {
        Lecture lecture = new Lecture("Test");

        Question question = new Question();
        question.setText("text?");

        serviceToTest.addQuestion(evaluation, question);
        return serviceToTest.save(lecture);
    }

}
