package com.clayfin.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayfin.enums.Status;
import com.clayfin.model.OtpEntity;
import com.clayfin.repository.OtpRepository;
import com.clayfin.request.dto.OtpDto;

@Service
public class OtpService {
	
	@Autowired
	OtpRepository repository;
	
	public void saveOtp(OtpEntity entity) {
		repository.save(entity);
	}
	
	public OtpEntity getLastOtp(String email) {
		List<OtpEntity> otps = repository.getLastOtp(email);
		return otps.get(0);
	}

	public Status checkOtp(OtpDto dto) {
		String otp = getLastOtp(dto.getEmail()).getOtp();
		if(dto.getOtp().equals(otp)) {
			return Status.ACCEPTED;
		}
		return Status.CONFLICT;
	}

}
