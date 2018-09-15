package br.com.caelum.feel.feedback.questions.domain.models;

import br.com.caelum.feel.feedback.companyteams.domain.models.CompanyTeam;

public interface AverageValuePerTeamnResult {

	Number getValue();
	
	CompanyTeam getTeam();	
}
