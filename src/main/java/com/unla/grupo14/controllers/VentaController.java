package com.unla.grupo14.controllers;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

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
    
    @GetMapping("")
	public RedirectView index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    return new RedirectView(ViewRouteHelper.VENTA_ADMIN);
                } else if (authority.getAuthority().equals("ROLE_USER")) {
                    return new RedirectView(ViewRouteHelper.VENTA_USUARIO);
                }
            }
        }
        
        return new RedirectView(ViewRouteHelper.VENTA_FORM);
    }
    
    // Método para mostrar ventas al usuario
    
    @GetMapping("/usuario")
    public String mostrarVentasUsuario(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.obtenerUserPorUsername(username);

        List<Venta> ventasUsuario = ventaService.obtenerVentasPorUsuario(user.getIdUser());
        model.addAttribute("ventasUsuario", ventasUsuario);

        return ViewRouteHelper.VENTA_LISTA_USUARIO;
    }

    // Método para mostrar ventas al administrador
    
    @GetMapping("/admin")
    public String mostrarVentasAdmin(Model model) {
    	
        List<Venta> ventas = ventaService.obtenerTodasLasVentas();
        model.addAttribute("ventasAdmin", ventas);

        return ViewRouteHelper.VENTA_LISTA_ADMIN;
    }
    
    @GetMapping("/registrar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("venta", new Venta());
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User loggedInUser = userService.obtenerUserPorUsername(username);

        model.addAttribute("loggedInUser", loggedInUser);

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

            Item item = new Item();
            item.setProducto(producto);
            item.setCantidad(cantidad);
            item.setVenta(venta);

            // Asignar el item a la venta
            if (venta.getItems() == null) {
                venta.setItems(new HashSet<>());
            }
            
            venta.getItems().add(item);

            // Guardar la venta (con el item incluido)
            ventaService.registrarVenta(venta);

            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return ViewRouteHelper.VENTA_FORM;
        }
    }
}