package com.sourceplate.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourceplate.model.User;



public interface UserRepository extends JpaRepository<User, Integer> {

	User findByMobilenumber(String phone);

}
