package com.unla.grupo14.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo14.entities.UserRole;
import com.unla.grupo14.repositories.IUserRoleRepository;
import com.unla.grupo14.services.IUserRoleService;

@Service("userRoleService")
public class UserRoleService implements IUserRoleService {
	
	@Autowired
	private IUserRoleRepository userRoleRepository;
	
	public UserRole saveUserRole(UserRole userRole){
		return userRoleRepository.save(userRole);
	}
	
}
