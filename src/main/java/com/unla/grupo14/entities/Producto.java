package com.unla.grupo14.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    @Column(name="codigo", nullable = false, unique = true)
    private long codigo;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="descripcion", length = 255)
    private String descripcion;

    @Column(name="costo", nullable = false)
    private double costo;

    @Column(name="precioVenta", nullable = false)
    private double precioVenta;

    @OneToOne(mappedBy = "producto")
    private Stock stock;

    @OneToMany(mappedBy = "producto")
    private Set<Item> item;
    
    @OneToOne(mappedBy = "producto")
    private Pedido pedido;
    
    @OneToMany(mappedBy = "producto")
    private Set<Lote> lote;
}