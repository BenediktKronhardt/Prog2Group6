package de.hsba.bi.demo6.evaluationFormTest;

import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormRepository;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormService;
import de.hsba.bi.demo6.evaluationForm.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@MockitoSettings
public class EvaluationFormServiceUnitTest {


    @Mock
    private EvaluationFormRepository evaluationFormRepositoryTest;

    @InjectMocks
    private EvaluationFormService evaluationFormServiceTest;


    @Test
    public void shouldReturnEvaluationForms() {
        //given, findet alle Evaluationsbögen und speichert diese (über das Repo mit Mokito)
        EvaluationForm evaluationForm = new EvaluationForm();
        EvaluationForm evaluationForm2 = new EvaluationForm();
        Mockito.when(evaluationFormRepositoryTest.findAll()).thenReturn(List.of(evaluationForm, evaluationForm2));

        //when, alle Evaluationsbögen die gespeichert wurden, werden abgerufen
        final Collection<EvaluationForm> evaluationForms = evaluationFormServiceTest.getAll();

        //then, Überprüft die Länge/ Menge der Evaluationsbögen
        assertThat(evaluationForms).hasSize(2).contains(evaluationForm,evaluationForm2);
        verify(evaluationFormRepositoryTest, never()).findById(new Long(2));

    }

    @Test
    void shouldReturnEvaluationFormById(){
        //given, selbes Prinzip wie oben, nur geben wir hier noch die ID mit
        Optional<EvaluationForm> evaluationForm = Optional.ofNullable(new EvaluationForm());
        EvaluationForm evaluationForm2 = new EvaluationForm();
        Mockito.when(evaluationFormRepositoryTest.findById(new Long(3))).thenReturn(evaluationForm);

        //when, Alle Evaluationsbögen aus der Repo werden uns gegeben
        Optional<EvaluationForm> givenEvaluationForm = Optional.ofNullable(evaluationFormServiceTest.getEvaluationForm(new Long(3)));

        //then, Überprüft, ob die Evaluationsbögen gleich ihrer ID sind
        assertThat(givenEvaluationForm).isEqualTo(evaluationForm);
        verify(evaluationFormRepositoryTest, never()).findAll();
    }

}
