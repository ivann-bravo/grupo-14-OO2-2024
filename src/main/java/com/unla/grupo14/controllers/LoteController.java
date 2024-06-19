package com.unla.grupo14.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo14.entities.Lote;
import com.unla.grupo14.entities.Pedido;
import com.unla.grupo14.entities.Producto;
import com.unla.grupo14.services.IAlmacenService;
import com.unla.grupo14.services.ILoteService;
import com.unla.grupo14.services.IPedidoService;
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
    
    @Autowired
    private IPedidoService pedidoService;
    
    @GetMapping("")
    public String index(Model model) {
        List<Lote> lotes = loteService.obtenerTodosLosLotes();
        model.addAttribute("lotes", lotes);
        return ViewRouteHelper.LOTE_LIST;
    }
    
    @GetMapping("/registrar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("lote", new Lote());
        
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);
        
        return ViewRouteHelper.LOTE_FORM;
    }
    
    @PostMapping("/registrar")
    public RedirectView registrarLote(@ModelAttribute("lote") Lote lote,
    		@RequestParam("productoId") int productoId, Model model) {
        try {
            Producto producto = productoService.findById(productoId);
            lote.setProducto(producto);
            lote.setAlmacen(almacenService.obtenerAlmacenUnico());
            loteService.registrarLote(lote);
            
            return new RedirectView(ViewRouteHelper.LOTE);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return new RedirectView(ViewRouteHelper.LOTE_FORM);
        }
    }
    
    @PostMapping("/registrarPedido")
    public RedirectView completarCompra(@RequestParam("pedidoId") int pedidoId, Model model) {
        Pedido pedido = pedidoService.findById(pedidoId);
        if (pedido == null) {
            model.addAttribute("error", "Pedido no encontrado");
            return new RedirectView(ViewRouteHelper.PEDIDO);
        }
        
        Lote lote = new Lote();
        lote.setCantidad(pedido.getCantidadPedida());
        lote.setFechaRecepcion(LocalDate.now());
        lote.setProveedor(pedido.getProveedor());
        lote.setPrecioCompra(pedido.getCantidadPedida()*pedido.getProducto().getCosto()); //dinamico
        lote.setProducto(pedido.getProducto());
        lote.setAlmacen(almacenService.obtenerAlmacenUnico());
        
        loteService.registrarLote(lote);
        pedidoService.deleteById(pedidoId);
        return new RedirectView(ViewRouteHelper.PEDIDO);
    }
}
