package com.example.sistemapedidos.routes.orders;

import com.example.sistemapedidos.domain.entities.OrderItens;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class OrdersCalculation extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:ordersCalculation")
                .log("${body}")
                .setBody(simple("${body.itens}"))
                .log("${body}")
                .log("## CALCULATING ORDERS")
                .unmarshal().json(JsonLibrary.Jackson, OrderItens.class.arrayType())
                .split(body())
                .log("${body}")
                .to("jpa:com.example.sistemapedidos.domain.repositories.IProductRepository.findByEan()")
                .end()

        ;
    }
}
