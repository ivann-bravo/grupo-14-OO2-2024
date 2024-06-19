package com.unla.grupo14.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import com.unla.grupo14.entities.Pedido;
import com.unla.grupo14.entities.Producto;
import com.unla.grupo14.entities.Stock;
import com.unla.grupo14.repositories.IPedidoRepository;
import com.unla.grupo14.repositories.IStockRepository;
import com.unla.grupo14.services.IPedidoService;

@Service
@SessionScope
public class PedidoService implements IPedidoService {

    @Autowired
    private IStockRepository stockRepository;

    @Autowired
    private IPedidoRepository pedidoRepository;
    
    private List<String> warnings = new ArrayList<>();

    @Override
    @Transactional
    @Scheduled(fixedRate = 30000) // Ejecutar cada 30 segundos
    public void verificarStock() {
        List<Stock> stocks = stockRepository.findAll();
        warnings.clear();

        for (Stock s : stocks) {
            if (s.getCantidadAlmacenada() < s.getCantMinima()) {
                Producto producto = s.getProducto();
                
                warnings.add("El producto " + producto.getNombre() + " tiene menos stock que el mínimo requerido.");

                // Verificar si ya existe un pedido para este producto
                if (!pedidoRepository.existsByProducto(producto)) {
                    Pedido pedido = new Pedido();

                    // Calcular la cantidad a pedir
                    int cantidadFaltante = s.getCantMinima() - s.getCantidadAlmacenada();
                    int cantidadPedida = s.getCantMinima() + cantidadFaltante; // para tener el doble de la cantidad minima

                    pedido.setCantidadPedida(cantidadPedida);
                    pedido.setProducto(producto);
                    pedido.setProveedor("Proveedor Predeterminado");

                    // Guardar el pedido
                    pedidoRepository.save(pedido);
                }
            }
        }
    }
    
    public List<Pedido> findAllPedidos() {
        return pedidoRepository.findAll();
    }
    
    @Override
    public void savePedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }
    
    @Override
    public Pedido findById(int id) {
        return pedidoRepository.findById(id).orElse(null);
    }
    
    @Override
    public void deleteById(int id) {
        pedidoRepository.deleteById(id);
    }
    
    public List<String> getWarnings() {
        return warnings;
    }
}
