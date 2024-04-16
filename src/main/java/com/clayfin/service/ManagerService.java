package com.clayfin.service;

import java.util.List;

import com.clayfin.enums.Status;
import com.clayfin.exception.TempUserException;
import com.clayfin.exception.UserException;
import com.clayfin.model.TempUserProfile;
import com.clayfin.request.dto.StatusDto;

import jakarta.servlet.http.HttpServletRequest;

public interface ManagerService {
	
	public List<TempUserProfile> getAllPendingUsers(HttpServletRequest request)throws UserException;
	
	public Status updateStatus(HttpServletRequest request,StatusDto dto) throws UserException, TempUserException;
}
