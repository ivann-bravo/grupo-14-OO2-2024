package com.unla.grupo14.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.grupo14.entities.Pedido;
import com.unla.grupo14.helpers.ViewRouteHelper;
import com.unla.grupo14.repositories.IPedidoRepository;
import com.unla.grupo14.services.IPedidoService;
import com.unla.grupo14.services.implementation.PedidoService;
import com.unla.grupo14.services.implementation.ProductoService;

import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private ProductoService productoService;

    @GetMapping("/lista")
    public String listarPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.findAllPedidos();
        model.addAttribute("pedidos", pedidos);
        return ViewRouteHelper.PEDIDO_LIST; // Ruta correcta a la plantilla Thymeleaf
    }
    
    @GetMapping("/registrar")
    public String mostrarFormularioDePedido(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());
        return ViewRouteHelper.PEDIDO_FORM; // Ruta a la plantilla del formulario de pedido
    }

    @PostMapping("/registrar")
    public String registrarPedido(@ModelAttribute("pedido") Pedido pedido) {
        pedidoService.savePedido(pedido);
        return "redirect:/pedidos/lista"; // Redirige a la lista de pedidos después de guardar
    }
}