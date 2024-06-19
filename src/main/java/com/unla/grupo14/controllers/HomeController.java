package com.unla.grupo14.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo14.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping("/index")
	public ModelAndView index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName(); 
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.INDEXADMIN);
                    modelAndView.addObject("username", username); 
                    return modelAndView;
                } else if (authority.getAuthority().equals("ROLE_USER")) {
                    ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.INDEXUSER);
                    modelAndView.addObject("username", username);
                    return modelAndView;
                }
            }
        }
        
        return new ModelAndView(new RedirectView(ViewRouteHelper.LOGIN));
    }

	@GetMapping("/")
	public RedirectView redirectToHomeIndex() {
		return new RedirectView(ViewRouteHelper.ROUTE);
	}
}
