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
@RequestMapping("/almacenes")
public class AlmacenController {
/*
	@Autowired
    private IAlmacenService almacenService;
    
    @GetMapping("")
    public String index(Model model) {
        List<Lote> lotes = loteService.obtenerTodosLosLotes();
        model.addAttribute("lotes", lotes);
        return ViewRouteHelper.LOTE_LIST;
    }
    
    @GetMapping("/registrar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("lote", new Almacen());
        return ViewRouteHelper.ALMACEN_FORM;
    }
    
    @PostMapping("/registrar")
    public String registrarLote(@ModelAttribute("almacen") Almacen almacen, Model model) {
        try {
            almacenService.registrarAlmacen(almacen);
            return "redirect:/lotes";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return ViewRouteHelper.LOTE_FORM;
        }
    }
*/
}