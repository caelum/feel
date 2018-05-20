package br.com.caelum.feel.feedback.companyteams.domain.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="teams_aud")
@IdClass(LastCompanyTeamRevisionId.class)
public class LastCompanyTeamVersion implements Serializable{

	@Id	
	private Integer rev;	
	
	@ManyToOne
	@JoinColumn(name="id")
	@Id
	private CompanyTeam team;
	
	@Column(name="total_expected_people")
	private int totalExpectedPeople;
	
	/**
	 * @deprecated
	 */
	public LastCompanyTeamVersion() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rev == null) ? 0 : rev.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LastCompanyTeamVersion other = (LastCompanyTeamVersion) obj;
		if (rev == null) {
			if (other.rev != null)
				return false;
		} else if (!rev.equals(other.rev))
			return false;
		return true;
	}
	
	public CompanyTeam getTeam() {
		return team;
	}
	
	public int getTotalExpectedPeople() {
		return totalExpectedPeople;
	}
	
}
