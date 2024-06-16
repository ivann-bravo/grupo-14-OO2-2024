package com.unla.grupo14.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter @NoArgsConstructor
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLote;

    @Column(name="cantidad", nullable = false)
    private int cantidad;

    @Column(name="fechaRecepcion", nullable = false)
    private LocalDate fechaRecepcion;

    @Column(name="proveedor", nullable = false)
    private String proveedor;

    @Column(name="precioCompra", nullable = false)
    private double precioCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "almacen_id", nullable = false)
    private Almacen almacen;
}