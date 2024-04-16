package com.clayfin.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.clayfin.common.util.AbstractClass;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "TEMP_USER_PROFILE" ,
 uniqueConstraints = @UniqueConstraint(columnNames =  {"EMAIL","USERNAME"}))
@Data
public class TempUserProfile extends AbstractClass {
	
	  @NotBlank
	  @Size(max = 20)
	  @Column(name = "USERNAME")
	  private String username;

	  @NotBlank
	  @Size(max = 50)
	  @Email
	  @Column(name = "EMAIL")
	  private String email;
	  
	  @ManyToMany(fetch = FetchType.LAZY)
	  @JoinTable(  name = "TEMP_USER_PROFILE_ROLES", 
	        joinColumns = @JoinColumn(name = "temp_user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "temp_role_id"))
	  @JsonIgnore
	  private Set<Role> roles = new HashSet<>();
}
