package com.example.sistemapedidos.domain.entities.orders.converter;

import com.example.sistemapedidos.domain.entities.orders.Orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrdersConverter implements Converter<String, Orders> {

    private ObjectMapper objectMapper;

    public void JsonToOrderConverter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Orders convert(String source) {
        try {
            // Usa o ObjectMapper do Jackson para deserializar o JSON para um objeto Orders
            return objectMapper.readValue(source, Orders.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao converter JSON para Orders", e);
        }
    }
}
