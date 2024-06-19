package com.unla.grupo14.services;

import java.util.List;

import com.unla.grupo14.entities.Venta;

public interface IVentaService {

	void registrarVenta(Venta venta);
	public List<Venta> obtenerTodasLasVentas();
	public List<Venta> obtenerVentasPorUsuario(int idUser);
}
