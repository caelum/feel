package br.com.caelum.feel.behavior;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BehaviorFeedbackRepository extends CrudRepository<BehaviorFeedback, Integer>{

}
