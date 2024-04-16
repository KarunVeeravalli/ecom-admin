package com.clayfin.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayfin.common.util.RepoHelper;
import com.clayfin.enums.ERole;
import com.clayfin.enums.Status;
import com.clayfin.exception.TempUserException;
import com.clayfin.exception.UserException;
import com.clayfin.model.RequestedUsers;
import com.clayfin.model.Role;
import com.clayfin.model.TempUserProfile;
import com.clayfin.model.User;
import com.clayfin.repository.RequestedUsersRepo;
import com.clayfin.repository.RoleRepository;
import com.clayfin.repository.UserProfileRepository;
import com.clayfin.repository.UserRepo;
import com.clayfin.request.dto.StatusDto;
import com.clayfin.service.ManagerService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ManagerServiceImpl implements ManagerService{
	
	@Autowired RepoHelper helper;
	
	@Autowired UserRepo repo;
	
	@Autowired UserProfileRepository tempUserRepo;
	
	@Autowired RequestedUsersRepo requestedUsersRepo;
	
	@Autowired RoleRepository roleRepository;
	@Override
	public List<TempUserProfile> getAllPendingUsers(HttpServletRequest request) throws UserException {
		System.out.println("inside controller");
		List<RequestedUsers> reqUsers = requestedUsersRepo.findAllByStatus("PENDING");
		if(reqUsers.size()!=0) {
			List<TempUserProfile> tempUserProfiles = new ArrayList<>();
			for(RequestedUsers i : reqUsers) {
				TempUserProfile tempUserProfile = tempUserRepo.findById(i.getTempUserId()).get();
				tempUserProfiles.add(tempUserProfile);
			}
			return tempUserProfiles;
		}
		return null;
	}

	@Override
	public Status updateStatus(HttpServletRequest request, StatusDto dto) throws UserException, TempUserException {
			User profile = helper.getUserProfile(request);
			if(profile.getRoles().contains(new Role(ERole.ROLE_MANAGER))) {
				try {
					TempUserProfile tempUserProfile = tempUserRepo.findById(dto.getId()).get();
					if(tempUserProfile!=null && dto.getStatus().equals("ACCEPTED")) {
						User user = new User();
						user.setEmail(tempUserProfile.getEmail());
						user.setUsername(tempUserProfile.getUsername());
						Set<Role> roles = new HashSet<>();
						Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER);
						roles.add(userRole);
						user.setRoles(roles);
						user.setIsHavingPermission(true);
						user.setIsSuperAdmin(true);
						repo.save(user);
						
						requestedUsersRepo.deleteByTempUserId(dto.getId());
						tempUserRepo.deleteById(dto.getId());
						
						
						return Status.UPDATED;
					}
				} catch (Exception e) {
					throw new TempUserException("No User Found with userId : "+dto.getId());
				}
				
			}
			else{
				throw new UserException("Only Manager can give permission to give access");
			}
		 return null;
	}
	
	
}
