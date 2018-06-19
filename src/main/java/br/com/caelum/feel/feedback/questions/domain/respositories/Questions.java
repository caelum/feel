package br.com.caelum.feel.feedback.questions.domain.respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import br.com.caelum.feel.feedback.questions.domain.models.Question;

public interface Questions extends Repository<Question, Long>,JpaSpecificationExecutor<Question> {

    Optional<Question> findById(Long id);

    void save(Question question);

    List<Question> findAll();

    Question findByHash(String hash);

	Optional<Question> findByLastOneAndCycleId(boolean lastOne, Integer cycleId);

	List<Question> findByCycleIdOrderByDueDateAsc(@Param("id") Integer cycleId,Pageable pageable);

	List<Question> findByCycleIdOrderByDueDateAsc(Integer cycleId);

	//TODO um ponto interessante é que aqui ficou complicado de testar... tem um parâmetro na query que não é definido pelo programador
	@Query("select q from Question q where q.dueDate >= now() and q.cycle.deletedInstant is null")
	List<Question> findAllCurrentOpenQuestions();
}
