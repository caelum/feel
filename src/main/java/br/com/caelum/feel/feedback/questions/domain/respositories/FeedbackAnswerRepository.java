package br.com.caelum.feel.feedback.questions.domain.respositories;

import org.springframework.stereotype.Repository;

import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;

@Repository
public interface FeedbackAnswerRepository extends org.springframework.data.repository.Repository<FeedbackAnswer, Integer>{

	 @SuppressWarnings("unchecked")
	FeedbackAnswer save(FeedbackAnswer entity);
}
