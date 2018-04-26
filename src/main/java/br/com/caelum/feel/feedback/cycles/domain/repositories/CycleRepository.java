package br.com.caelum.feel.feedback.cycles.domain.repositories;

import br.com.caelum.feel.feedback.cycles.domain.models.Cycle;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CycleRepository extends Repository<Cycle, Integer> {

    List<Cycle> findAll();

    Optional<Cycle> findById(Integer cycleId);

    void save(Cycle cycle);
}
