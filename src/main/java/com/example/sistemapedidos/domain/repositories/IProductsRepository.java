package com.example.sistemapedidos.domain.repositories;

import com.example.sistemapedidos.domain.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductsRepository extends JpaRepository<Products, Long> {
}
