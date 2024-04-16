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


@Entity
@Table(name = "USERS", 
uniqueConstraints = { 
		  @UniqueConstraint(columnNames = "USERNAME"),
		  @UniqueConstraint(columnNames = "EMAIL") 
		})
public class User extends AbstractClass{
	 @NotBlank
	  @Size(max = 20)
	  @Column(name = "USERNAME")
	  private String username;

	  @NotBlank
	  @Size(max = 50)
	  @Email
	  @Column(name = "EMAIL")
	  private String email;


	  @OneToMany(mappedBy = "users")
	  @JsonIgnore
	  private List<Address> address;
	  
	  
	  @OneToMany(mappedBy = "users")
	  @JsonIgnore
	  private List<Orders> orders;
	  

	  @OneToMany(mappedBy = "users")
	  @JsonIgnore
	  private List<Transaction> transactions;
	  
	  
	  @ManyToMany(fetch = FetchType.LAZY)
	  @JoinTable(  name = "USER_PROFILE_ROLES", 
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "role_id"))
	  @JsonIgnore
	  private Set<Role> roles = new HashSet<>();
	  
	  @Lob
	  private String profileImg;
	  private Boolean isSuperAdmin;
	  private Boolean isHavingPermission;
	  private Boolean isACustomer;
	  
	  private Boolean isActive;
	  
	public User() {
		
	}
	


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Boolean getIsSuperAdmin() {
		return isSuperAdmin;
	}

	public void setIsSuperAdmin(Boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	public Boolean getIsHavingPermission() {
		return isHavingPermission;
	}

	public void setIsHavingPermission(Boolean isHavingPermission) {
		this.isHavingPermission = isHavingPermission;
	}

	public Boolean getIsACustomer() {
		return isACustomer;
	}

	public void setIsACustomer(Boolean isNotACustomer) {
		this.isACustomer = isNotACustomer;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
