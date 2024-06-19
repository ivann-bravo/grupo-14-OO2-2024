package com.unla.grupo14.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.grupo14.entities.Venta;
import com.unla.grupo14.entities.Item;
import com.unla.grupo14.entities.Producto;
import com.unla.grupo14.entities.Stock;
import com.unla.grupo14.repositories.IVentaRepository;
import com.unla.grupo14.services.IVentaService;
import com.unla.grupo14.repositories.IStockRepository;

@Service
public class VentaService implements IVentaService{

	@Autowired
	private IVentaRepository ventaRepository;

	@Autowired
	private IStockRepository stockRepository;

	@Transactional
    @Override
    public void registrarVenta(Venta venta) {
        try {
            // Guardar la venta en la base de datos
            Venta nuevaVenta = ventaRepository.save(venta);

            // Obtener el item asociado a la venta
            for(Item item : nuevaVenta.getItems()) {
            	if (item != null) {
                    Producto producto = item.getProducto();
                    Stock stock = producto.getStock();

                    if (stock != null) {
                        // Calcular la nueva cantidad en el stock
                        int nuevaCantidad = stock.getCantidadAlmacenada() - item.getCantidad();
                        if (nuevaCantidad < 0) {
                            throw new IllegalArgumentException("No hay suficiente stock para el producto " + producto.getNombre());
                        }
                        // Actualizar la cantidad en el stock
                        stock.setCantidadAlmacenada(nuevaCantidad);
                        stockRepository.save(stock);
                    } else {
                        throw new IllegalArgumentException("El producto " + producto.getNombre() + " no tiene stock asociado");
                    }
                } else {
                    throw new IllegalArgumentException("La venta no tiene un item asociado");
                }
            }
            
        } catch (Exception e) {
            
            throw new RuntimeException("Error al registrar la venta: " + e.getMessage(), e);
        }
    }
	
	@Override
    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }

	@Override
    public List<Venta> obtenerVentasPorUsuario(int userId) {
        return ventaRepository.findByUser_IdUser(userId);
    }  
}