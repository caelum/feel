package br.com.caelum.feel.feedback.companyteams.application.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.caelum.feel.feedback.companyteams.application.forms.TeamForm;
import br.com.caelum.feel.feedback.companyteams.domain.models.CompanyTeam;
import br.com.caelum.feel.feedback.companyteams.domain.repositories.Teams;

@Service
public class SaveTeamService {
    private final Teams teams;

    public SaveTeamService(Teams teams) {
        this.teams = teams;
    }

    public CompanyTeam saveByForm(@Valid TeamForm form) {
        var optionalTeam = Optional.ofNullable(form.getId()).flatMap(teams::findById);

        optionalTeam.ifPresent(team -> team.updateFromForm(form));

        var team = optionalTeam.orElseGet(form::toEntity);

        teams.save(team);
        
        return team;
    }

    public void fillFormOnlyWhenIdIsPresent(Optional<Long> optionalId, TeamForm form) {
        optionalId
                .flatMap(teams::findById)
                .ifPresent(form::fillFrom);
    }
}