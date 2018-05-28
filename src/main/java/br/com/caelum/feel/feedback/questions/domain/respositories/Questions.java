package br.com.caelum.feel.feedback.questions.domain.respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import br.com.caelum.feel.feedback.questions.domain.models.Question;

public interface Questions extends Repository<Question, Long> {

    Optional<Question> findById(Long id);

    void save(Question question);

    Page<Question> findAll(Pageable pageable);

    void delete(Question question);

    Question findByHash(String hash);

	Optional<Question> findByLastOneAndCycleId(boolean lastOne, Integer cycleId);

	List<Question> findByCycleIdOrderByDueDateAsc(@Param("id") Integer cycleId,Pageable pageable);

	List<Question> findByCycleIdOrderByDueDateAsc(Integer cycleId);
}
