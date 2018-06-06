package br.com.caelum.feel.feedback.reports.application.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;
import br.com.caelum.feel.infra.AsyncLocalEndpointExecutor;

@Component
public class UpdateQuestionsReportEndpoint {
	
	@Autowired
	private AsyncLocalEndpointExecutor endpointExecutor;

	public void execute(FeedbackAnswer feedbackAnswer) {
		endpointExecutor.post("/magic/kjfhsdjkfdsfsduhwied23/reports/feedback/views/per-team/{answerId}", feedbackAnswer.getId());		
	}
}
