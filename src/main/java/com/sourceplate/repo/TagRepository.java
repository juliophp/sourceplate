package com.sourceplate.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourceplate.model.Tag;


public interface TagRepository extends JpaRepository<Tag, Integer> {

	Tag findByName(String string);
	boolean existsTagByName(String roleName);

}
