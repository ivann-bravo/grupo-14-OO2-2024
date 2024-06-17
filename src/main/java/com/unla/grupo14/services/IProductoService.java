package com.unla.grupo14.services;

import java.util.List;
import com.unla.grupo14.entities.Producto;

public interface IProductoService {
	
    Producto registrarProducto(Producto producto, int cantMinima);
    
    List<Producto> obtenerTodosLosProductos();
    
    Producto findById(int idProducto);
    
    List<Producto> obtenerProductosSinLoteAsociado();
}
