package br.com.caelum.feel.feedback.questions.domain.respositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;

@Repository
public interface FeedbackAnswerRepository extends CrudRepository<FeedbackAnswer, Integer>{

	Number countByQuestionIdAndTeamId(Long questionId,Long teamId);
	
	@Query("select sum(f.value) from FeedbackAnswer f where f.question.id = :questionId and f.team.id = :teamId") 
	BigDecimal sumValuesOfQuestion(@Param("questionId") Long questionId,@Param("teamId") Long teamId);

	List<FeedbackAnswer> findByTeamIdAndQuestionId(Long teamId, Long questionId);

	List<FeedbackAnswer> findByQuestionId(Long teamId);
}
