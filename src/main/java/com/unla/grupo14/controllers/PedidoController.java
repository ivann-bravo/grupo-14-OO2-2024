package com.unla.grupo14.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo14.entities.Pedido;
import com.unla.grupo14.helpers.ViewRouteHelper;
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
    
    @GetMapping("")
	public String index(Model model) {
    	List<Pedido> pedidos = pedidoService.findAllPedidos();
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("warnings", pedidoService.getWarnings());
        return ViewRouteHelper.PEDIDO_LIST;
	}

    @GetMapping("/lista")
    public RedirectView listarPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.findAllPedidos();
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("warnings", pedidoService.getWarnings());
        return new RedirectView(ViewRouteHelper.PEDIDO);
    }
    
    @GetMapping("/registrar")
    public String mostrarFormularioDePedido(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());
        return ViewRouteHelper.PEDIDO_FORM;
    }

    @PostMapping("/registrar")
    public RedirectView registrarPedido(@ModelAttribute("pedido") Pedido pedido) {
        pedidoService.savePedido(pedido);
        return new RedirectView(ViewRouteHelper.PEDIDO);
    }
}