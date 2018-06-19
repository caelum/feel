package br.com.caelum.feel.feedback.reports.application.validators;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.caelum.feel.feedback.companyteams.domain.models.CompanyTeam;
import br.com.caelum.feel.feedback.companyteams.domain.repositories.Teams;
import br.com.caelum.feel.feedback.reports.application.forms.SearchRawAnswersForm;
import br.com.caelum.feel.feedback.security.AuthenticatedUser;
import br.com.caelum.feel.security.SystemUser;

public class UserIsLeaderOfTeamValidator implements Validator {

	private Teams teamRepository;
	private SystemUser currentUser;
	private AuthenticatedUser authenticatedUser;

	public UserIsLeaderOfTeamValidator(Teams teamRepository, SystemUser currentUser, AuthenticatedUser authenticatedUser) {
		this.teamRepository = teamRepository;
		this.currentUser = currentUser;
		this.authenticatedUser = authenticatedUser;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return SearchRawAnswersForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SearchRawAnswersForm form = (SearchRawAnswersForm) target;
		
		if (authenticatedUser.isReader(currentUser)) {
			List<CompanyTeam> teams = teamRepository.findByLeaderLogin(currentUser.getEmail());
			if(!teams.stream().filter(t -> t.getId().equals(form.getTeamId())).findAny().isPresent()) {
				errors.rejectValue("teamId", "", "Você não está cadastrado como lider do time");
			}
		}		
		
	}

}
