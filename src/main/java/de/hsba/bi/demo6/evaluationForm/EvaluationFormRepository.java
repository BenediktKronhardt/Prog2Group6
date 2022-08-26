package de.hsba.bi.demo6.evaluationForm;

import org.springframework.data.jpa.repository.JpaRepository;

interface EvaluationFormRepository extends JpaRepository<EvaluationForm, Long>{

@Repository
class EvaluationFormRepository {

    private Map<Integer, EvaluationForm> store = new TreeMap<>();
    private AtomicInteger sequence = new AtomicInteger();

//  Ein EvaluationForm Objekt wird in der Map "store" gespeichert.
//  Durch "sequence.incrementAndGet()" wird eine neue ID vom Typ Integer automatisch generiert.
//  In store.put wird das neue evaluationForm Objekt der Map zugeordnet mit der vorher generierten ID
    EvaluationForm save(EvaluationForm evaluationForm) {
        int id = sequence.incrementAndGet();
        evaluationForm.setId(id);
        store.put(id, evaluationForm);
        return evaluationForm;
    }

//  Einzelne EvaluationForms per ID finden
    Optional<EvaluationForm> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }

//  Alle Einträge der Map "store" (alle Evaluationsbögen) finden
    Collection<EvaluationForm> findAll(){
        return store.values();
    }

//  EvaluationForm Element aus der Map "store" löschen
    void deleteById(Integer id){
        store.remove(id);
    }


}


