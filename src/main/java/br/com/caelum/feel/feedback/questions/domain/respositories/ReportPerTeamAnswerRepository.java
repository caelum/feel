package br.com.caelum.feel.feedback.questions.domain.respositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.caelum.feel.feedback.questions.domain.models.ReportPerTeamAnswer;

@Repository
public interface ReportPerTeamAnswerRepository extends CrudRepository<ReportPerTeamAnswer, Integer>{

}
