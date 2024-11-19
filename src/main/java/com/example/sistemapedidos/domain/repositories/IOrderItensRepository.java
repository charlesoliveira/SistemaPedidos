package com.example.sistemapedidos.domain.repositories;

import com.example.sistemapedidos.domain.entities.OrderItens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderItensRepository extends JpaRepository<OrderItens, Integer> {
}
