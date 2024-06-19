package com.unla.grupo14.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo14.entities.Almacen;
import com.unla.grupo14.repositories.IAlmacenRepository;
import com.unla.grupo14.services.IAlmacenService;

@Service("almacenService")
public class AlmacenService implements IAlmacenService   {

    @Autowired
    private IAlmacenRepository almacenRepository;
    
    @Override
    public Almacen obtenerAlmacenUnico() {
        return almacenRepository.findAll().stream().findFirst().orElse(null);
    }
    
    @Override
    public Almacen registrarAlmacen(Almacen almacen) {
        
        return almacenRepository.save(almacen);
    }
    
    @Override
    public List<Almacen> obtenerTodosLosAlmacenes() {
        return almacenRepository.findAll();
    }
    
    @Override
    public Almacen obtenerAlmacenPorId(int id) {
        return almacenRepository.findById(id).orElse(null);
    }
    
}
