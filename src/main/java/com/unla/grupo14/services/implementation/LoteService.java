package com.unla.grupo14.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo14.entities.Almacen;
import com.unla.grupo14.entities.Lote;
import com.unla.grupo14.repositories.ILoteRepository;
import com.unla.grupo14.services.IAlmacenService;
import com.unla.grupo14.services.ILoteService;

@Service("loteService")
public class LoteService implements ILoteService  {

	@Autowired
    private ILoteRepository loteRepository;
	
	@Autowired
    private IAlmacenService almacenService;
    
	@Override
    public Lote registrarLote(Lote lote) {
    	
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