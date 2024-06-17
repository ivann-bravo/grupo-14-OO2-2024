package com.unla.grupo14.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.grupo14.entities.Almacen;
import com.unla.grupo14.services.IAlmacenService;
import com.unla.grupo14.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/almacen")
public class AlmacenController {

	@Autowired
    private IAlmacenService almacenService;
    
    @GetMapping("")
    public String index(Model model) {
        List<Almacen> almacenes = almacenService.obtenerTodosLosAlmacenes();
        model.addAttribute("almacen", almacenes);
        return ViewRouteHelper.ALMACEN_LIST;
    }
    
    @GetMapping("/registrar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("almacen", new Almacen());
        return ViewRouteHelper.ALMACEN_FORM;
    }
    
    @PostMapping("/registrar")
    public String registrarAlmacen(@ModelAttribute("almacen") Almacen almacen, Model model) {
        try {
            almacenService.registrarAlmacen(almacen);
            return "redirect:/almacen";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return ViewRouteHelper.ALMACEN_FORM;
        }
    }

}