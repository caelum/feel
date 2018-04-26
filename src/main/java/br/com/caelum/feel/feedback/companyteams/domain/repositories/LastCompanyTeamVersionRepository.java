package br.com.caelum.feel.feedback.companyteams.domain.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.caelum.feel.feedback.companyteams.domain.models.LastCompanyTeamVersion;

@Repository
public interface LastCompanyTeamVersionRepository extends org.springframework.data.repository.Repository<LastCompanyTeamVersion, Integer>{

	@Query("select team from LastCompanyTeamVersion team where team.rev = (select max(team2.rev) from LastCompanyTeamVersion team2 where team2.team.id = team.team.id)")
	Set<LastCompanyTeamVersion> bla();

	
}
