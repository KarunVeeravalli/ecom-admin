package com.clayfin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clayfin.model.User;

public interface UserRepo extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
