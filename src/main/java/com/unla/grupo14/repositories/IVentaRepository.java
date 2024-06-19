package com.unla.grupo14.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo14.entities.Venta;

@Repository("ventaRepository")
public interface IVentaRepository extends JpaRepository<Venta, Serializable> {
	List<Venta> findByUser_IdUser(int idUser);
}