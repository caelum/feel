package br.com.caelum.feel.feedback.domain.companyteams.repositories;

import br.com.caelum.feel.feedback.domain.companyteams.models.CompanyTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface Teams extends Repository<CompanyTeam, Long> {
    Page<CompanyTeam> findAll(Pageable pageable);

    Optional<CompanyTeam> findById(Long id);

    void save(CompanyTeam companyTeam);

    void delete(CompanyTeam team);
}
