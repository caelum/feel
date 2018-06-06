package br.com.caelum.feel.feedback.companyteams.application.services;

import br.com.caelum.feel.feedback.companyteams.application.forms.TeamForm;
import br.com.caelum.feel.feedback.companyteams.domain.models.CompanyTeam;
import br.com.caelum.feel.feedback.companyteams.domain.repositories.Teams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

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

    public Page<CompanyTeam> getAllPaged(Integer currentPage) {
        var pageRequest = PageRequest.of(currentPage, 5);

        return teams.findAll(pageRequest);
    }

    public void fillFormOnlyWhenIdIsPresent(Optional<Long> optionalId, TeamForm form) {
        optionalId
                .flatMap(teams::findById)
                .ifPresent(form::fillFrom);
    }

    public Optional<CompanyTeam> removeById(Long id) {

        var team = teams.findById(id);

        team.ifPresent(teams::delete);

        return team;
    }
}