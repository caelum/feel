package br.com.caelum.feel.feedback.companyteams.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.caelum.feel.feedback.companyteams.domain.models.Cycle;

@Repository
public interface CycleRepository extends CrudRepository<Cycle, Integer>{

}
