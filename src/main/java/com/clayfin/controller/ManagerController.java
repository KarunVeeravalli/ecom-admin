package com.clayfin.controller;

import java.util.List;

import org.apache.camel.ServiceStatus;
import org.apache.logging.log4j.status.StatusData;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clayfin.enums.Status;
import com.clayfin.exception.TempUserException;
import com.clayfin.exception.UserException;
import com.clayfin.model.TempUserProfile;
import com.clayfin.request.dto.StatusDto;
import com.clayfin.service.ManagerService;

import jakarta.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired ManagerService service;
	
	@GetMapping("/getAllRequets")
	public List<TempUserProfile> findAllPendingRequests(HttpServletRequest request) throws UserException{
		System.out.println("inside controller");
		return service.getAllPendingUsers(request);
	}
	
	@PutMapping("/updateStatus")
	public Status updateStatus(HttpServletRequest request,@RequestBody StatusDto dto) throws UserException, TempUserException {
		try {
			return service.updateStatus(request, dto);
		} catch (Exception e) {
			return Status.FAILURE;
		}
		
	}


}
