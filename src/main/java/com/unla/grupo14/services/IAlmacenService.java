package com.unla.grupo14.services;

import java.util.List;

import com.unla.grupo14.entities.Almacen;

public interface IAlmacenService {

	Almacen registrarAlmacen(Almacen almacen);
	
	List<Almacen> obtenerTodosLosAlmacenes();
}
