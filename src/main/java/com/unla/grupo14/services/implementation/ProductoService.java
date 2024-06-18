package com.unla.grupo14.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.unla.grupo14.entities.Producto;
import com.unla.grupo14.entities.Stock;
import com.unla.grupo14.repositories.IProductoRepository;
import com.unla.grupo14.repositories.IStockRepository;
import com.unla.grupo14.services.IProductoService;

import jakarta.transaction.Transactional;

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

        Stock stock = new Stock();
        stock.setProducto(nuevoProducto);
        stock.setCantidadAlmacenada(0);
        stock.setCantMinima(cantMinima);
        stockRepository.save(stock);

        nuevoProducto.setStock(stock);
		
        return productoRepository.save(nuevoProducto);
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
	
    @Override
    @Transactional
    public Producto modificarProducto(Producto productoModificado, int nuevaCantMinima) {
        Producto productoExistente = productoRepository.findById(productoModificado.getIdProducto())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        productoExistente.setCodigo(productoModificado.getCodigo());
        productoExistente.setNombre(productoModificado.getNombre());
        productoExistente.setDescripcion(productoModificado.getDescripcion());
        productoExistente.setCosto(productoModificado.getCosto());
        productoExistente.setPrecioVenta(productoModificado.getPrecioVenta());
        
        Stock stock = productoExistente.getStock();
        if (stock != null) {
            stock.setCantMinima(nuevaCantMinima);
            stockRepository.save(stock);
        }
        
        return productoRepository.save(productoExistente);
    }
    
    @Override
    @Transactional
    public String verificarAsociaciones(Producto producto) {
        if (producto.getLote() != null) {
            return "El producto tiene lote asociado y no se puede eliminar.";
        }

        if (producto.getItem() != null) {
            return "El producto tiene item asociado y no se puede eliminar.";
        }

        if (producto.getPedido() != null) {
            return "El producto tiene pedido asociado y no se puede eliminar.";
        }

        if (producto.getStock() != null) {
            return null; // Permitir eliminación con confirmación si tiene stock asociado
        }

        return null; // No tiene asociaciones que impidan la eliminación
    }

    @Override
    @Transactional
    public void eliminarProducto(int id) throws DataIntegrityViolationException {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        stockRepository.delete(producto.getStock());
        productoRepository.delete(producto);
    }
}