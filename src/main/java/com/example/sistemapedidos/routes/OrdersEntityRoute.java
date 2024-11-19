package com.example.sistemapedidos.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class OrdersEntityRoute extends RouteBuilder {

    @Autowired
    DataSource dataSource;

    @Override
    public void configure() throws Exception {
        from("activemq:queue:com.example.SistemaA.orders")
                .routeId("OrdersEntityRoute")
                .setHeader("initialBody", simple("${body}"))
                .log("${body}")
                .to("jpa://com.example.sistemapedidos.domain.repositories.IOrdersRepository")
                .to("activemq:queue.com.example.SistemaB.integration");

    }
}
