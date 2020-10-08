package com.sourceplate.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourceplate.model.Category;



public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByName(String string);
	boolean existsCategoryByName(String roleName);

}
