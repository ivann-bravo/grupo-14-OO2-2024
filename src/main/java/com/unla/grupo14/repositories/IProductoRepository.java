package com.unla.grupo14.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo14.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> {
	
	Producto findByCodigo(long codigo);

	//@Query("SELECT u FROM User u JOIN FETCH u.userRoles WHERE u.username = (:username)")
	//public abstract Producto findByUsernameAndFetchUserRolesEagerly(@Param("username") String username);
}
