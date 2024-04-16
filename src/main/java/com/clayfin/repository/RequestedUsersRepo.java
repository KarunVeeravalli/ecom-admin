package com.clayfin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clayfin.model.RequestedUsers;

import jakarta.transaction.Transactional;

public interface RequestedUsersRepo extends JpaRepository<RequestedUsers, Long>{

	List<RequestedUsers> findAllByStatus(String string);
	
	@Transactional
	void deleteByTempUserId(Long id);


	
}
