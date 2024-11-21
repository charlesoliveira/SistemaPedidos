package com.example.sistemapedidos.domain.repositories;

import com.example.sistemapedidos.domain.entities.orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdersRepository extends JpaRepository<Orders, Long> {

}
