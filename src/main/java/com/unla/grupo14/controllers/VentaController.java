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

import com.unla.grupo14.entities.Item;
import com.unla.grupo14.entities.Producto;
import com.unla.grupo14.entities.User;
import com.unla.grupo14.entities.Venta;
import com.unla.grupo14.services.IProductoService;
import com.unla.grupo14.services.IVentaService;
import com.unla.grupo14.services.implementation.UserService;
import com.unla.grupo14.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
    private IVentaService ventaService;

    @Autowired
    private UserService userService;

    @Autowired
    private IProductoService productoService;

    @GetMapping("/registrar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("venta", new Venta());

        List<User> users = userService.obtenerTodosLosUser();
        model.addAttribute("users", users);

        List<Producto> productos = productoService.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);

        return ViewRouteHelper.VENTA_FORM;
    }

    @PostMapping("/registrar")
    public String registrarVenta(@ModelAttribute("venta") Venta venta,
                                 @RequestParam("userId") int userId,
                                 @RequestParam("productoId") int productoId,
                                 @RequestParam("cantidad") int cantidad,
                                 Model model) {
        try {
            User user = userService.obtenerUserPorId(userId);
            venta.setUser(user);

            Producto producto = productoService.findById(productoId);
            if (producto == null) {
                throw new IllegalArgumentException("Producto no encontrado");
            }

            // Crear el ítem y asociarlo a la venta
            Item item = new Item();
            item.setProducto(producto);
            item.setCantidad(cantidad);
            item.setVenta(venta);

            // Asignar el item a la venta
            venta.setItem(item);

            // Guardar la venta (con el item incluido)
            ventaService.registrarVenta(venta);

            return "redirect:/ventas";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return ViewRouteHelper.VENTA_FORM;
        }
    }

}