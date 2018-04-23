package br.com.caelum.feel.feedback.application.admin.companyteams.forms;

import br.com.caelum.feel.feedback.domain.companyteams.models.CompanyTeam;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TeamForm {
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    @Min(1)
    private Integer totalExpectedPeople;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalExpectedPeople() {
        return totalExpectedPeople;
    }

    public void setTotalExpectedPeople(Integer totalExpectedPeople) {
        this.totalExpectedPeople = totalExpectedPeople;
    }

    public void fillFrom(CompanyTeam companyTeam) {
        id = companyTeam.getId();
        name = companyTeam.getName();
        totalExpectedPeople = companyTeam.getTotalExpectedPeople();
    }

    public CompanyTeam toEntity() {
        return new CompanyTeam(id, name, totalExpectedPeople);
    }
}
