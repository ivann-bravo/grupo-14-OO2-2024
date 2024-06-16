package com.unla.grupo14.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo14.entities.Lote;
import com.unla.grupo14.entities.Producto;

@Repository("loteRepository")
public interface ILoteRepository extends JpaRepository<Lote, Serializable> {

}