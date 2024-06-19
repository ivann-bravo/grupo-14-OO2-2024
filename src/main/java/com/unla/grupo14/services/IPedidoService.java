package com.unla.grupo14.services;

import java.util.List;

import com.unla.grupo14.entities.Pedido;

public interface IPedidoService {
	public void verificarStock();
	List<Pedido> findAllPedidos();
	void savePedido(Pedido pedido);
	public Pedido findById(int id);
	public void deleteById(int id);
	public List<String> getWarnings();
}
