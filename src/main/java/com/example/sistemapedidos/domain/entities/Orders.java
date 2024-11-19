package com.example.sistemapedidos.domain.entities;

import com.example.sistemapedidos.domain.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="tb_orders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime date;

    @Column(name="code")
    private String code;

    @Column(name="orderStatus")
    private OrderStatus orderStatus;

    @Column(name = "subTotal")
    private double subTotal;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients client;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItens> itens = new HashSet<>();
}
