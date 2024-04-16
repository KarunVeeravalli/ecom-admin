package com.clayfin.service;

import com.clayfin.enums.Status;
import com.clayfin.exception.UserException;
import com.clayfin.exception.UserLoginProfileException;
import com.clayfin.model.TempUserProfile;

import jakarta.servlet.http.HttpServletRequest;

public interface UserProfileService {
	
	
	public Status addUser(TempUserProfile profile, HttpServletRequest request) throws UserException, UserLoginProfileException;
	
	public Status updateUser(TempUserProfile profile, HttpServletRequest request) throws UserException;
	
	public TempUserProfile getUserProfile(HttpServletRequest request) throws UserException;
	
	public Status deleteUser(HttpServletRequest request) throws UserException;
	
//	public Status addManager(SignupRequest loginProfile,HttpServletRequest request) throws UserException,UserLoginProfileException;
}
