package com.clayfin.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayfin.common.util.RepoHelper;
import com.clayfin.enums.Status;
import com.clayfin.exception.AddressException;
import com.clayfin.exception.UserException;
import com.clayfin.model.Address;
import com.clayfin.model.TempUserProfile;
import com.clayfin.model.User;
import com.clayfin.repository.AddressRepository;
import com.clayfin.service.AddressService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	AddressRepository repository;
	
	@Autowired
	RepoHelper helper;

	@Override
	public Status addAddress(Address address, HttpServletRequest request) throws AddressException, UserException {
		User profile = helper.getUserProfile(request);
		address.setUsers(profile);
		repository.save(address);
		return Status.CREATED;
	}

	@Override
	public List<Address> getAddress(HttpServletRequest request) throws AddressException, UserException {
		User profile = helper.getUserProfile(request);
		List<Address> addresses = repository.findAllByUsersId(profile.getId());
		if(addresses.equals(null)) {
			throw new AddressException("No address found by user : "+profile.getUsername());
		}
		return addresses;
	}

	@Override
	public Status updateAddress(Long id, Address address, HttpServletRequest request)
			throws AddressException, UserException {
		User profile = helper.getUserProfile(request);
		Address oldAddress = repository.findById(id).get();
		BeanUtils.copyProperties(address, oldAddress, helper.getNullPropertyNames(address));
		if(!oldAddress.getUsers().getId().equals(profile.getId())) {
			throw new AddressException("Entered Address is incorrect please verify again");
		}
		repository.save(oldAddress);
		return Status.UPDATED;
	}

	@Override
	public Address getAddress(Long id, HttpServletRequest request) throws AddressException, UserException {
		User profile = helper.getUserProfile(request);
		Address address = repository.findById(id).get();
		if(!address.getUsers().getId().equals(profile.getId())) {
			throw new AddressException("Entered Address is incorrect please verify again");
		}
		return address;
	}

	@Override
	public Status deleteAddress(Long id, HttpServletRequest request) throws AddressException, UserException {
		User profile = helper.getUserProfile(request);
		Address address = repository.findById(id).get();
		if(!address.getUsers().getId().equals(profile.getId())) {
			throw new AddressException("Entered Address is incorrect please verify again");
		}
		repository.deleteById(id);
		return Status.DELETED;
	}

}
