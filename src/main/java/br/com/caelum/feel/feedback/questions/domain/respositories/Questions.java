package br.com.caelum.feel.feedback.questions.domain.respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import br.com.caelum.feel.feedback.questions.domain.models.Question;

public interface Questions extends Repository<Question, Long>,QueryByExampleExecutor<Question> {

    Optional<Question> findById(Long id);

    void save(Question question);

    Page<Question> findAll(Pageable pageable);
    
    List<Question> findAll();

    void delete(Question question);

    Question findByHash(String hash);

	Optional<Question> findByLastOneAndCycleId(boolean lastOne, Integer cycleId);

	List<Question> findByCycleIdOrderByDueDateAsc(@Param("id") Integer cycleId,Pageable pageable);

	List<Question> findByCycleIdOrderByDueDateAsc(Integer cycleId);

	//TODO um ponto interessante é que aqui ficou complicado de testar... tem um parâmetro na query que não é definido pelo programador
	@Query("select q from Question q where q.dueDate >= now()")
	List<Question> findAllCurrentOpenQuestions();
}
