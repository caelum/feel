package br.com.caelum.feel.security;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserDao extends CrudRepository<SystemUser, Integer>{

	Optional<SystemUser> findByEmail(String email);

	@Query("select s from SystemUser s join s.roles r where r.name = :role")
	List<SystemUser> findByRole(@Param("role") String role);
}
