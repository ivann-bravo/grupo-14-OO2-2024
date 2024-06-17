package com.unla.grupo14.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.grupo14.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> {
	
	Producto findByCodigo(long codigo);
	
	@Query("SELECT p FROM Producto p WHERE NOT EXISTS (SELECT 1 FROM Lote l WHERE l.producto = p)")
	public abstract List<Producto> findProductosSinLoteAsociado();
}
