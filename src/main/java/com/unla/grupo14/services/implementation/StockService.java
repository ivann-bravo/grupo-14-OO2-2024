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

	
}
