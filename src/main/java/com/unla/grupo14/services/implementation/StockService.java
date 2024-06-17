package com.unla.grupo14.services.implementation;

import com.unla.grupo14.entities.Stock;
import com.unla.grupo14.repositories.IStockRepository;
import com.unla.grupo14.services.IStockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService implements IStockService {

	@Autowired
	private IStockRepository stockRepository;

	@Autowired
	private EmailService emailService;

	public void verificarNivelesDeStock() {
		List<Stock> stocks = stockRepository.findAll();
		for (Stock stock : stocks) {
			if (stock.getCantidadAlmacenada() <= stock.getCantMinima()) {
				// Generar alerta de reabastecimiento
				generarAlerta(stock);
			}
		}
	}
	private void generarAlerta(Stock stock) {
		String mensaje = "El producto " + stock.getProducto().getDescripcion()
				+ " ha alcanzado el nivel mínimo de stock. Cantidad actual: " + stock.getCantidadAlmacenada();
		emailService.enviarAlertaReabastecimiento(mensaje);
	}

	@Scheduled(fixedRate = 3600000) // Ejecutar cada 1 hora
	public void verificarNivelesDeStockPeriodicamente() {
		verificarNivelesDeStock();
	}
}
