package br.com.caelum.feel.feedback.questions.domain.respositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;

@Repository
public interface FeedbackAnswerRepository extends CrudRepository<FeedbackAnswer, Integer>{

	Number countByQuestionIdAndTeamId(Long questionId,Long teamId);
}
