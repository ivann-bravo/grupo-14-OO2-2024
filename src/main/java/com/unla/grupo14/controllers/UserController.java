package com.unla.grupo14.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo14.entities.User;
import com.unla.grupo14.entities.UserRole;
import com.unla.grupo14.helpers.ViewRouteHelper;
import com.unla.grupo14.services.implementation.UserService;


@Controller
public class UserController {

	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewRouteHelper.USER_LOGIN;
	}

	@GetMapping("/logout")
	public String logout(Model model) {
		return ViewRouteHelper.USER_LOGOUT;
	}

	@GetMapping("/loginsuccess")
	public RedirectView loginCheck() {
		return new RedirectView(ViewRouteHelper.ROUTE);
	}
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/registerform")
	public String mostrarFormulario(Model model) {
		model.addAttribute("user", new User());
		
		return ViewRouteHelper.USER_REGISTERFORM;
		
	}
	
	@PostMapping("/registerform")
	public String registrarUsuario(@ModelAttribute("user") User user, @RequestParam("role") String role, Model model) {
		try {

			BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
			String p = pe.encode(user.getPassword());
			user.setPassword(p);
			
			UserRole userRole = new UserRole(user, "ROLE_USER");
			user.agregar(userRole);
			
			if (role.equals("ROLE_ADMIN")) {
				UserRole userRoleAdmin = new UserRole(user, role);
				user.agregar(userRoleAdmin);
			}
			
			userService.saveUser(user);
			
			model.addAttribute("message", "User registered successfully");
			
			return ViewRouteHelper.USER_LOGIN;
		} catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return ViewRouteHelper.USER_REGISTERFORM;
        }
	}
}
