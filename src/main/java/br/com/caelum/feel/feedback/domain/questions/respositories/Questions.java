package br.com.caelum.feel.feedback.domain.questions.respositories;

import br.com.caelum.feel.feedback.domain.questions.models.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface Questions extends Repository<Question, Long> {

    Optional<Question> findById(Long id);

    void save(Question question);

    Page<Question> findAll(Pageable pageable);
}
