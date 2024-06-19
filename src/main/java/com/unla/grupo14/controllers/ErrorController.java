package com.unla.grupo14.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.unla.grupo14.helpers.ViewRouteHelper;

@Controller
public class ErrorController {
	
	@GetMapping("/access-denied")
    public String accessDenied(Model model) {
        model.addAttribute("error", "No tienes los permisos necesarios para acceder a esta página.");
        return ViewRouteHelper.INDEXUSER;
    }
}
