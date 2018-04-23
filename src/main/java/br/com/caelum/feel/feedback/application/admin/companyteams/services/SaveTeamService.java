package br.com.caelum.feel.feedback.application.admin.companyteams.services;

import br.com.caelum.feel.feedback.application.admin.companyteams.forms.TeamForm;
import br.com.caelum.feel.feedback.domain.companyteams.models.CompanyTeam;
import br.com.caelum.feel.feedback.domain.companyteams.repositories.Teams;
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

    public void saveByForm(@Valid TeamForm form) {
        var optionalTeam = Optional.ofNullable(form.getId()).flatMap(teams::findById);

        optionalTeam.ifPresent(team -> team.updateFromForm(form));

        var team = optionalTeam.orElseGet(form::toEntity);

        teams.save(team);
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