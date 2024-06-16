package com.unla.grupo14.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo14.entities.Almacen;
import com.unla.grupo14.entities.Lote;
import com.unla.grupo14.repositories.IAlmacenRepository;
import com.unla.grupo14.repositories.ILoteRepository;
import com.unla.grupo14.services.ILoteService;

@Service("loteService")
public class LoteService implements ILoteService  {

	@Autowired
    private ILoteRepository loteRepository;
	
    @Autowired
    private IAlmacenRepository almacenRepository;
    
    public Lote registrarLote(Lote lote) {
    	
    	Lote nuevoLote = loteRepository.save(lote);
    	
    	Almacen almacen = nuevoLote.getAlmacen();
    	
    	almacen.setCantidadAlmacenada(almacen.getCantidadAlmacenada() + nuevoLote.getCantidad());
        almacenRepository.save(almacen);
        
        return nuevoLote;
    }
    
    @Override
    public List<Lote> obtenerTodosLosLotes() {
        return loteRepository.findAll();
    }
}