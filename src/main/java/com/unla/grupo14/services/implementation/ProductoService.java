package com.unla.grupo14.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo14.entities.Producto;
import com.unla.grupo14.entities.Stock;
import com.unla.grupo14.repositories.IProductoRepository;
import com.unla.grupo14.repositories.IStockRepository;
import com.unla.grupo14.services.IProductoService;

@Service("productoService")
public class ProductoService implements IProductoService {
	
	@Autowired
	private IProductoRepository productoRepository;
	
	@Autowired
	private IStockRepository stockRepository;

	@Override
	public Producto registrarProducto(Producto producto, int cantMinima) {
		
		if (productoRepository.findByCodigo(producto.getCodigo()) != null) {
            throw new IllegalArgumentException("El código del producto ya existe");
        }
		
		Producto nuevoProducto = productoRepository.save(producto);

        // Crear y asociar el stock al producto
        Stock stock = new Stock();
        stock.setProducto(nuevoProducto);
        stock.setCantidadAlmacenada(0);
        stock.setCantMinima(cantMinima);
        stockRepository.save(stock);

        nuevoProducto.setStock(stock);
		
        return productoRepository.save(nuevoProducto);// Guardar nuevamente para actualizar la relación
    }

	@Override
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }
	
	@Override
    public Producto findById(int idProducto) {
        return productoRepository.findById(idProducto).orElse(null);
    }

	@Override
	public List<Producto> obtenerProductosSinLoteAsociado() {
		return productoRepository.findProductosSinLoteAsociado();
	}
}