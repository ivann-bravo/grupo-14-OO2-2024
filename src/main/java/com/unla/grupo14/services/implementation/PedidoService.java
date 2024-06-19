package com.unla.grupo14.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.grupo14.entities.Pedido;
import com.unla.grupo14.entities.Producto;
import com.unla.grupo14.entities.Stock;
import com.unla.grupo14.repositories.IPedidoRepository;
import com.unla.grupo14.repositories.IStockRepository;
import com.unla.grupo14.services.IPedidoService;

@Service
public class PedidoService implements IPedidoService {

    @Autowired
    private IStockRepository stockRepository;

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Override
    @Transactional
    @Scheduled(fixedRate = 30000) // Ejecutar cada 30 segundos
    public void verificarStock() {
        List<Stock> stocks = stockRepository.findAll();

        for (Stock s : stocks) {
            if (s.getCantidadAlmacenada() < s.getCantMinima()) {
                Producto producto = s.getProducto();

                // Verificar si ya existe un pedido para este producto
                if (!pedidoRepository.existsByProducto(producto)) {
                    Pedido pedido = new Pedido();

                    // Calcular la cantidad a pedir
                    int cantidadFaltante = s.getCantMinima() - s.getCantidadAlmacenada();
                    int cantidadPedida = cantidadFaltante * 2; // Al menos el doble de lo que falta

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
}
