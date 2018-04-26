package br.com.caelum.feel.feedback.cycles.domain.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.caelum.feel.feedback.cycles.domain.models.Cycle;

@Repository
public interface CycleRepository extends CrudRepository<Cycle, Integer>{
	

}
