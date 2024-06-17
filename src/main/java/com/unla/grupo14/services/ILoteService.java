package com.unla.grupo14.services;

import java.util.List;

import com.unla.grupo14.entities.Lote;

public interface ILoteService {

	Lote registrarLote(Lote lote);
	
	List<Lote> obtenerTodosLosLotes();
}