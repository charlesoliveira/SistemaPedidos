package com.example.sistemapedidos.controllers;

import com.example.sistemapedidos.domain.entities.Clients;
import com.example.sistemapedidos.domain.entities.OrderItens;
import com.example.sistemapedidos.domain.entities.Orders;
import com.example.sistemapedidos.domain.entities.Products;
import com.example.sistemapedidos.domain.repositories.IClientsRepository;
import com.example.sistemapedidos.domain.repositories.IOrderItensRepository;
import com.example.sistemapedidos.domain.repositories.IOrdersRepository;
import com.example.sistemapedidos.domain.repositories.IProductsRepository;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path =  "/v1")
public class OrdersController {

    @Autowired
    private IOrdersRepository ordersRepository;

    @Autowired
    private IOrderItensRepository orderItensRepository;

    @Autowired
    private IClientsRepository clientsRepository;

    @Autowired
    private IProductsRepository productsRepository;


    @GetMapping(path = "/orders")
    public ResponseEntity<List<Orders>> buscarListagemOrders() {
        List<Orders> output = ordersRepository.findAll();
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @GetMapping(path = "/orderItens")
    public ResponseEntity<List<OrderItens>> buscarListagemOrderItens() {
        List<OrderItens> output = orderItensRepository.findAll();
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @GetMapping(path = "/clients")
    public ResponseEntity<List<Clients>> buscarListagemClients() {
        List<Clients> output = clientsRepository.findAll();
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<Products>> buscarListagemProducts() {
        List<Products> output = productsRepository.findAll();
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
