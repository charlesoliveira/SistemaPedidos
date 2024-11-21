package com.example.sistemapedidos.routes.orders;

import com.example.sistemapedidos.domain.entities.OrderItens;
import com.example.sistemapedidos.domain.entities.orders.Orders;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import static org.apache.camel.LoggingLevel.INFO;

@Component
public class OrdersEntityRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("activemq:queue:com.example.SistemaA.orders")
                .routeId("OrdersEntityRoute")
                .log("${body}")
                .unmarshal().json(JsonLibrary.Jackson, Orders.class)
                .process(exchange -> {
                    Orders orders = exchange.getIn().getBody(Orders.class);
                    exchange.getIn().setBody(orders);
                })
                .log("${body}")
                .to("jpa:com.example.sistemapedidos.domain.repositories.IOrdersRepository.saveAll()")
                .log("${body}")
                .setBody(simple("${body.itens}"))
                .split(body())
                .process(exchange -> {
                    OrderItens item = exchange.getIn().getBody(OrderItens.class);
                    exchange.getIn().setBody(item);
                })
                .log(INFO, "${body}")
        ;
    }
}
