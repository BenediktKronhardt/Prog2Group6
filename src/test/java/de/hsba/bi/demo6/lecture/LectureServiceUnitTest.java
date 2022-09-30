package de.hsba.bi.demo6.lecture;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.List;


import org.assertj.core.api.AbstractFileAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;


@MockitoSettings
public class LectureServiceUnitTest {

    @Mock
    private LectureRepository lectureRepository;

    @InjectMocks
    private LectureService service;

    @Test
    void shouldFindAllIfSearchIsEmpty() {
        Lecture lecture = new Lecture("test");
        Mockito.when(lectureRepository.findAll()).thenReturn(List.of(lecture));

        final List<Lecture> lectures = service.findLectures("");

        Assertions.assertThat(lectures).hasSize(1).contains(lecture);
        verify(lectureRepository, never()).findByEntryDescription(anyString());
    }

    @Test
    void shouldFindByDescriptionIfSearchIsNotEmpty() {
        Lecture lecture = new Lecture("test");
        Mockito.when(lectureRepository.findByEntryDescription(anyString())).thenReturn(List.of(lecture));

        final List<Lecture> lectures = service.findLectures("something");

        assertThat(lectures).hasSize(1).contains(lecture);
        verify(lectureRepository, never()).findAll();
    }


}
