package com.unla.grupo14.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo14.entities.Almacen;
import com.unla.grupo14.entities.Lote;
import com.unla.grupo14.repositories.IAlmacenRepository;
import com.unla.grupo14.services.IAlmacenService;
/*
@Service("almacenService")
public class AlmacenService implements IAlmacenService   {

    @Autowired
    private IAlmacenRepository almacenRepository;
    
    public Almacen registrarAlmacen(Almacen almacen) {
    	
    	Almacen nuevoAlmacen = almacenRepository.save(almacen);
    	
    	Almacen almacen = nuevoLote.getAlmacen();
    	
    	almacen.setCantidadAlmacenada(almacen.getCantidadAlmacenada() + nuevoLote.getCantidad());
        almacenRepository.save(almacen);
        
        return nuevoLote;
    }
    
    @Override
    public List<Almacen> obtenerTodosLosAlmacenes() {
        return almacenRepository.findAll();
    }
    
}
*/