package com.unla.grupo14.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo14.entities.Producto;
import com.unla.grupo14.repositories.IProductoRepository;
import com.unla.grupo14.services.IProductoService;

@Service("productoService")
public class ProductoService implements IProductoService {
	
	@Autowired
	private IProductoRepository productoRepository;

	@Override
	public Producto registrarProducto(Producto producto) {
		
		if (productoRepository.findByCodigo(producto.getCodigo()) != null) {
            throw new IllegalArgumentException("El código del producto ya existe");
        }
		
        return productoRepository.save(producto);
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