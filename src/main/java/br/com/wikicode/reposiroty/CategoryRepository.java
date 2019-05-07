package br.com.wikicode.reposiroty;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wikicode.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	@Transactional(readOnly=true)
	Category findByName(String name);

}
