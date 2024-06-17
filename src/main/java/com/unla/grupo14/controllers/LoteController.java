package com.unla.grupo14.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unla.grupo14.entities.Lote;
import com.unla.grupo14.entities.Producto;
import com.unla.grupo14.services.IAlmacenService;
import com.unla.grupo14.services.ILoteService;
import com.unla.grupo14.services.IProductoService;
import com.unla.grupo14.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/lotes")
public class LoteController {

    @Autowired
    private ILoteService loteService;
    
    @Autowired
    private IAlmacenService almacenService;
    
    @Autowired
    private IProductoService productoService;
    
    @GetMapping("")
    public String index(Model model) {
        List<Lote> lotes = loteService.obtenerTodosLosLotes();
        model.addAttribute("lotes", lotes);
        return ViewRouteHelper.LOTE_LIST;
    }
    
    @GetMapping("/registrar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("lote", new Lote());
        
        List<Producto> productos = productoService.obtenerProductosSinLoteAsociado();
        model.addAttribute("productos", productos);
        
        return ViewRouteHelper.LOTE_FORM;
    }
    
    @PostMapping("/registrar")
    public String registrarLote(@ModelAttribute("lote") Lote lote, @RequestParam("productoId") int productoId, Model model) {
        try {
        	Producto producto = productoService.findById(productoId);
        	lote.setProducto(producto);
        	lote.setAlmacen(almacenService.obtenerAlmacenUnico());
            loteService.registrarLote(lote);
            return "redirect:/lotes";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return ViewRouteHelper.LOTE_FORM;
        }
    }
}
