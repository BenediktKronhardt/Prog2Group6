package de.hsba.bi.demo6.evaluationFormTest;


import de.hsba.bi.demo6.evaluationForm.EvaluationForm;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormRepository;
import de.hsba.bi.demo6.evaluationForm.EvaluationFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.LineSeparatorDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class EvaluationFormServiceIntegrationTest {

    @Autowired
    private EvaluationFormRepository evaluationFormRepository;

    @Autowired
    private EvaluationFormService evaluationFormService;

    @BeforeEach
    void delete(){
        evaluationFormRepository.deleteAll();
    }

    @Test
    void shouldFindAll(){

        //given, verschiedene Evaluationsbögen werden angelegt
        EvaluationForm evaluationForm = new EvaluationForm();
        EvaluationForm evaluationForm2 = new EvaluationForm();
        EvaluationForm evaluationForm3 = new EvaluationForm();
        EvaluationForm evaluationForm4 = new EvaluationForm();
        evaluationFormRepository.save(evaluationForm);
        evaluationFormRepository.save(evaluationForm2);
        evaluationFormRepository.save(evaluationForm3);
        evaluationFormRepository.save(evaluationForm4);

        //when, alle Evaluationsbögen werden aufgerufen
        final Collection<EvaluationForm> evaluationForms = evaluationFormService.getAll();

        //then, Überprüfen, ob alle Evaluationsbögen vorhanden sind, nach denen gesucht wird
        assertThat(evaluationForms).containsExactly(evaluationForm, evaluationForm2, evaluationForm3, evaluationForm4);

    }

}
