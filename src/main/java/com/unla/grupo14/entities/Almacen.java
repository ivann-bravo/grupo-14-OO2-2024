package com.unla.grupo14.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAlmacen;

    @Column(name="cantidadAlmacenada", nullable = false)
    private int cantidadAlmacenada;

    @Column(name="cantMinima", nullable = false)
    private int cantMinima;

    @OneToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "almacen")
    private Set<Lote> lotes = new HashSet<>();
}