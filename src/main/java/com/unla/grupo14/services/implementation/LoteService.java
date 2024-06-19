package com.unla.grupo14.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo14.entities.Almacen;
import com.unla.grupo14.entities.Lote;
import com.unla.grupo14.entities.Producto;
import com.unla.grupo14.entities.Stock;
import com.unla.grupo14.repositories.ILoteRepository;
import com.unla.grupo14.repositories.IStockRepository;
import com.unla.grupo14.services.IAlmacenService;
import com.unla.grupo14.services.ILoteService;

@Service("loteService")
public class LoteService implements ILoteService {

	@Autowired
	private ILoteRepository loteRepository;

	@Autowired
	private IAlmacenService almacenService;
	
	@Autowired
	private IStockRepository stockRepository;

	@Override
	public Lote registrarLote(Lote lote) {

		Producto producto = lote.getProducto();
		if (producto == null) {
			throw new IllegalArgumentException("El lote debe estar asociado a un producto");
		}

		// Obtener el stock asociado al producto
		Stock stock = producto.getStock();
		if (stock == null) {
			throw new IllegalArgumentException("El producto no tiene un stock asociado");
		}

		// Actualizar la cantidad almacenada en el stock
		int nuevaCantidadAlmacenada = stock.getCantidadAlmacenada() + lote.getCantidad();
		stock.setCantidadAlmacenada(nuevaCantidadAlmacenada);
		stockRepository.save(stock);

		Almacen almacen = almacenService.obtenerAlmacenUnico();
		if (almacen == null) {
			almacen = new Almacen();
			almacen = almacenService.registrarAlmacen(almacen);
		}

		lote.setAlmacen(almacen);

		return loteRepository.save(lote);
	}

	@Override
	public List<Lote> obtenerTodosLosLotes() {
		return loteRepository.findAll();
	}
}