package com.example.sistemapedidos.domain.repositories;

import com.example.sistemapedidos.domain.entities.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientsRepository extends JpaRepository<Clients, Long> {
}
