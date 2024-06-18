package com.unla.grupo14.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unla.grupo14.entities.UserRole;
import com.unla.grupo14.repositories.IUserRepository;
import com.unla.grupo14.repositories.IUserRoleRepository;

import jakarta.annotation.PostConstruct;

@Service("userService")
public class UserService implements UserDetailsService {

	private IUserRepository userRepository;
	

	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.unla.grupo14.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
    }
	
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		com.unla.grupo14.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
//		return buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
//	}

	private User buildUser(com.unla.grupo14.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
						true, true, true, //accountNonExpired, credentialsNonExpired, accountNonLocked,
						grantedAuthorities);
	}

	private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for(UserRole userRole: userRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<>(grantedAuthorities);
	}
	
	public List<com.unla.grupo14.entities.User> obtenerTodosLosUser() {
        return userRepository.findAll();
    }
	
	// para la pagina de registro de usuario: 
	
	private IUserRoleRepository userRoleRepository;
	
	public com.unla.grupo14.entities.User saveUser(com.unla.grupo14.entities.User user){
		return userRepository.save(user);
	}
	
	public UserRole saveUserRole(UserRole userRole){
		return userRoleRepository.save(userRole);
	}
	
	// para solucionar el error al no existir usuarios-roleusuario
	
	@PostConstruct
    public void init() {
        if (userRepository.count() == 0) {
        	// la primera ves iniciada el programa se crea un usuario admin
        	// el cual contiene los dos roles posibles
        	
        	// creamos los roles
            UserRole roleUser = new UserRole();
            roleUser.setRole("ROLE_USER");
            UserRole roleUser2 = new UserRole();
            roleUser2.setRole("ROLE_ADMIN");

            
            //ahora se crea el usuario
            com.unla.grupo14.entities.User user = new com.unla.grupo14.entities.User();
            user.setUsername("admin");
            user.setPassword("$2a$10$N8N2x/qp1DIcGzvmPGpGFuDcgVGR559xu6qWxdssLNeaMD7h6jR1u");
            user.setEnabled(true);
            user.setUserRoles(new HashSet<>(Set.of(roleUser)));
            
            //y a los roles le agregamos el usuario
            roleUser.setUser(user);
            roleUser2.setUser(user);
            
            //y ahora al reves
            user.agregar(roleUser);
            user.agregar(roleUser2);
            
            //y por ultimo guardamos el usuario, gracias al cascade se guardan junto al rol
            userRepository.save(user);
        }
	}
}
