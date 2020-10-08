package com.sourceplate.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourceplate.model.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String string);
	boolean existsRoleByName(String roleName);

}
