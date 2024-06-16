package com.unla.grupo14.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.grupo14.entities.Lote;
import com.unla.grupo14.services.ILoteService;
import com.unla.grupo14.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/lotes")
public class LoteController {

    @Autowired
    private ILoteService loteService;
    
    @GetMapping("")
    public String index(Model model) {
        List<Lote> lotes = loteService.obtenerTodosLosLotes();
        model.addAttribute("lotes", lotes);
        return ViewRouteHelper.LOTE_LIST;
    }
    
    @GetMapping("/registrar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("lote", new Lote());
        return ViewRouteHelper.LOTE_FORM;
    }
    
    @PostMapping("/registrar")
    public String registrarLote(@ModelAttribute("lote") Lote lote, Model model) {
        try {
            loteService.registrarLote(lote);
            return "redirect:/lotes";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return ViewRouteHelper.LOTE_FORM;
        }
    }
}
