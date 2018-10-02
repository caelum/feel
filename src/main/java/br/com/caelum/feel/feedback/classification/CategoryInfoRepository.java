package br.com.caelum.feel.feedback.classification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryInfoRepository extends CrudRepository<CategoryInfo, Integer> {

}
