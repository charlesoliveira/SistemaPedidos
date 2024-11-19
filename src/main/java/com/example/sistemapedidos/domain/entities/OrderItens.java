package com.example.sistemapedidos.domain.entities;

import com.example.sistemapedidos.domain.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="tb_orderItens")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItens implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "price")
    private double price;

//    @ManyToOne
//    @JoinColumn(name = "itens_id")
//    @JsonIgnore
//    private Orders orders;
}
