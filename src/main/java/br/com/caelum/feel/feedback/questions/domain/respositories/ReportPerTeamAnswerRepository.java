package br.com.caelum.feel.feedback.questions.domain.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.caelum.feel.feedback.questions.domain.models.AnswerCountPerQuestionResult;
import br.com.caelum.feel.feedback.questions.domain.models.AverageValuePerQuestionResult;
import br.com.caelum.feel.feedback.questions.domain.models.AverageValuePerTeamnResult;
import br.com.caelum.feel.feedback.questions.domain.models.CountValuePerQuestionResult;
import br.com.caelum.feel.feedback.questions.domain.models.ReportPerTeamAnswer;

@Repository
public interface ReportPerTeamAnswerRepository extends CrudRepository<ReportPerTeamAnswer, Integer>{

	@Query("select r from ReportPerTeamAnswer r where r.cycle.id = :cycleId and r.id in (select max(r2.id) from ReportPerTeamAnswer r2 where r2.cycle.id = :cycleId and r2.team.id = r.team.id and r2.question.id = r.question.id)")
	List<ReportPerTeamAnswer> listCurrentView(@Param("cycleId") Integer cycleId);
	
	@Query("select avg(fa.value) as value,fa.question as question from FeedbackAnswer fa where fa.question.cycle.id = :cycleId group by fa.question")
	List<AverageValuePerQuestionResult> averagePerQuestion(@Param("cycleId") Integer cycleId);

	@Query("select avg(fa.value) as value,fa.team as team from FeedbackAnswer fa where fa.question.cycle.id = :cycleId and fa.question.id = :questionId group by fa.team")
	List<AverageValuePerTeamnResult> averagePerTeam(@Param("cycleId") Integer cycleId, @Param("questionId") Long questionId);
	
	@Query("select sum(r.answersCount) as value,r.question as question from ReportPerTeamAnswer r where r.cycle.id = :cycleId and r.id in (select max(r2.id) from ReportPerTeamAnswer r2 where r2.cycle.id = :cycleId and r2.team.id = r.team.id and r2.question.id = r.question.id) group by r.question.id")
	List<AnswerCountPerQuestionResult> countPerQuestion(@Param("cycleId") Integer cycleId);

	@Query("select count(r.id) as countValue, r.feedbackAnswer.value as value from ReportPerTeamAnswer r where r.cycle.id = :cycleId  and r.question.id = :questionId group by r.feedbackAnswer.value")	
	List<CountValuePerQuestionResult> countValuesPerQuestion(@Param("cycleId") Integer cycleId, @Param("questionId") Long questionId);

}
