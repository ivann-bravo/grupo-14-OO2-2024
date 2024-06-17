package com.unla.grupo14.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unla.grupo14.entities.Producto;
import com.unla.grupo14.entities.Stock;
import com.unla.grupo14.services.IProductoService;
import com.unla.grupo14.services.IStockService;
import com.unla.grupo14.helpers.ViewRouteHelper;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;
    
    @Autowired
    private IStockService stockService;
    
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
    public String registrarProducto(@ModelAttribute("producto") Producto producto,
    		@RequestParam("cantMinima") int cantMinima, Model model) {
    	try {
    		Producto productoRegistrado = productoService.registrarProducto(producto);
            
            Stock stock = new Stock();
            stock.setCantidadAlmacenada(0); 
            stock.setCantMinima(cantMinima); 
            stock.setProducto(productoRegistrado);  
            
    		stockService.registrarStock(stock);
    		
            return "redirect:/productos";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return ViewRouteHelper.PRODUCTO_FORM;
        }
    }
}

