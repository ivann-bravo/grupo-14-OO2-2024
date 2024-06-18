package com.unla.grupo14.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.unla.grupo14.entities.Pedido;
import com.unla.grupo14.helpers.ViewRouteHelper;
import com.unla.grupo14.repositories.IPedidoRepository;
import com.unla.grupo14.services.IPedidoService;

import java.util.List;

@Controller
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

    @GetMapping("/lista-pedidos")
    public String listarPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.findAllPedidos();
        model.addAttribute("pedidos", pedidos);
        return ViewRouteHelper.PEDIDO_LIST; // Ruta correcta a la plantilla Thymeleaf
    }
}