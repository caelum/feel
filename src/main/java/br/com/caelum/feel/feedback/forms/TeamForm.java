package br.com.caelum.feel.feedback.forms;

import br.com.caelum.feel.feedback.domain.companyteams.models.CompanyTeam;

import javax.validation.constraints.NotEmpty;

public class TeamForm {
    private Long id;

    @NotEmpty
    private String name;

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

    @Override
    public String toString() {
        return "TeamForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void fillFrom(CompanyTeam companyTeam) {
        id = companyTeam.getId();
        name = companyTeam.getName();
    }

    public CompanyTeam toEntity() {
        return new CompanyTeam(id, name);
    }
}
