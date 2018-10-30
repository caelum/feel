package br.com.caelum.feel.feedback.companyteams.application.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.caelum.feel.feedback.companyteams.domain.models.CompanyTeam;

public class TeamForm {
	private Long id;

	@NotEmpty
	private String name;

	@NotNull
	@Min(1)
	private Integer totalExpectedPeople;

	@NotBlank
	private String leaderLogin;

	private boolean enable;

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getLeaderLogin() {
		return leaderLogin;
	}

	public void setLeaderLogin(String leaderName) {
		this.leaderLogin = leaderName;
	}

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
		leaderLogin = companyTeam.getLeaderLogin();
		totalExpectedPeople = companyTeam.getTotalExpectedPeople();
		enable = companyTeam.isEnable();
	}

	public CompanyTeam toEntity() {
		return new CompanyTeam(name, leaderLogin, totalExpectedPeople,enable);
	}
}
