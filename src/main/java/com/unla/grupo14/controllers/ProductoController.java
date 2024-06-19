package com.unla.grupo14.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unla.grupo14.entities.Producto;
import com.unla.grupo14.services.IPedidoService;
import com.unla.grupo14.services.IProductoService;
import com.unla.grupo14.helpers.ViewRouteHelper;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IPedidoService pedidoService;

	@GetMapping("")
	public String index(Model model) {
		List<Producto> productos = productoService.obtenerTodosLosProductos();
		List<String> warnings = pedidoService.getWarnings();
		model.addAttribute("productos", productos);
		model.addAttribute("warnings", warnings);
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
			productoService.registrarProducto(producto, cantMinima);
			return "redirect:/productos";
		} catch (IllegalArgumentException e) {
			model.addAttribute("error", e.getMessage());
			return ViewRouteHelper.PRODUCTO_FORM;
		}
	}
	
	@GetMapping("/modificar/{idProducto}")
    public String mostrarFormularioModificar(@PathVariable("idProducto") int id, Model model) {
        Producto producto = productoService.findById(id);
        model.addAttribute("producto", producto);
        return ViewRouteHelper.PRODUCTO_MODIFICAR;
    }
	
	@PostMapping("/modificar")
    public String modificarProducto(@ModelAttribute("producto") Producto productoModificado,
    		@RequestParam("cantMinima") int cantMinima, Model model) {
        productoService.modificarProducto(productoModificado, cantMinima);
		return "redirect:/productos";
    }
	
	@GetMapping("/eliminar/{idProducto}")
	public String confirmarEliminacion(@PathVariable("idProducto") int id, Model model) {
	    Producto producto = productoService.findById(id);
	    String mensaje = productoService.verificarAsociaciones(producto);

	    if (mensaje != null) {
	        model.addAttribute("mensaje", mensaje);
	        model.addAttribute("confirmarEliminacion", false);
	    } else {
	        model.addAttribute("mensaje", "El producto tiene stock asociado. ¿Desea eliminar el producto y su stock?");
	        model.addAttribute("confirmarEliminacion", true);
	    }
	    model.addAttribute("producto", producto);
	    List<Producto> productos = productoService.obtenerTodosLosProductos();
		model.addAttribute("productos", productos);
		return ViewRouteHelper.PRODUCTO_LIST;
	}

	@PostMapping("/eliminar/{idProducto}")
	public String eliminarProducto(@PathVariable("idProducto") int id, Model model) {
	    productoService.eliminarProducto(id);
	    return "redirect:/productos";
	}
	
}
