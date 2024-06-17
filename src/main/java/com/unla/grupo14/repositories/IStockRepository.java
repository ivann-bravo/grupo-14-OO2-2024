package com.unla.grupo14.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo14.entities.Stock;

@Repository("stockRepository")
public interface IStockRepository extends JpaRepository<Stock, Serializable> {

}