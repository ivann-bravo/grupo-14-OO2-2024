package com.unla.grupo14.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStock;

    @OneToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @OneToOne(mappedBy = "stock")
    private Almacen almacen;
    
    @OneToOne(mappedBy = "stock")
    private Pedido pedido;
}