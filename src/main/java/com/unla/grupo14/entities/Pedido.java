package com.unla.grupo14.entities;

import jakarta.persistence.Column;
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
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    @Column(name="cantidadPedida", nullable = false)
    private int cantidadPedida;

    @Column(name="proveedor", nullable = false)
    private String proveedor;
    
    @OneToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;
}