package com.unla.grupo14.services;

import java.util.List;

import com.unla.grupo14.entities.Pedido;

public interface IPedidoService {
	public void verificarStock();
	List<Pedido> findAllPedidos();
	
}
