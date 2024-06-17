package com.unla.grupo14.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.grupo14.entities.Producto;
import com.unla.grupo14.services.IProductoService;
import com.unla.grupo14.helpers.ViewRouteHelper;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping("")
    public String index(Model model) {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);
        return ViewRouteHelper.PRODUCTO_LIST;
    }

    @GetMapping("/registrar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        return ViewRouteHelper.PRODUCTO_FORM;
    }

    @PostMapping("/registrar")
    public String registrarProducto(@ModelAttribute("producto") Producto producto, Model model) {
    	try {
            productoService.registrarProducto(producto);
            return "redirect:/productos";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return ViewRouteHelper.PRODUCTO_FORM;
        }
    }
}

