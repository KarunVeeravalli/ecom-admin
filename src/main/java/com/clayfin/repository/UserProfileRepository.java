package com.clayfin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clayfin.model.TempUserProfile;

public interface UserProfileRepository extends JpaRepository<TempUserProfile, Long>{

	TempUserProfile findByUsername(String usernameFromToken);
	
	TempUserProfile findByEmail(String email);

}
