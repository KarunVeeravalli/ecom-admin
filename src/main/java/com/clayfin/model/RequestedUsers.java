package com.clayfin.model;

import com.clayfin.common.util.AbstractClass;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestedUsers extends AbstractClass{
	
	private Long tempUserId;
	private String status;
	
}
