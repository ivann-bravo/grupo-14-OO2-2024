package com.unla.grupo14.services;

import java.util.List;

import com.unla.grupo14.entities.User;
import com.unla.grupo14.entities.UserRole;

public interface IUserService {
	
	public List<User> obtenerTodosLosUser();
	
	public User obtenerUserPorId(int id);
	
	public User saveUser(User user);
	
}
