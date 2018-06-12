package br.com.caelum.feel.feedback.cycles.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.caelum.feel.feedback.cycles.domain.models.Cycle;

public interface CycleRepository extends CrudRepository<Cycle, Integer> {

}
