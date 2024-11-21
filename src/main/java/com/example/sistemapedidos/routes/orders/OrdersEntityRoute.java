package com.example.sistemapedidos.routes.orders;

import com.example.sistemapedidos.domain.entities.OrderItens;
import com.example.sistemapedidos.domain.entities.orders.Orders;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import static org.apache.camel.LoggingLevel.INFO;

@Component
public class OrdersEntityRoute extends RouteBuilder {

    protected static final String RETRIVE_ORDERID = "SELECT tb_orders.id FROM tb_orders WHERE tb_orders.code = '${exchangeProperty.ORDER_CODE}';";

    protected static final String RETRIVE_VALUES = "SELECT tb_products.price FROM tb_products WHERE ean ='${body.ean}';";

    protected static final String UPDATE_SUBTOTAL = "UPDATE tb_orders SET subtotal = '${body}' WHERE code = '${exchangeProperty.ORDER_CODE}';";


    @Autowired
    DataSource dataSource;

    private double subtotal = 0;

    @Override
    public void configure() throws Exception {
        from("activemq:queue:com.example.SistemaA.orders")
                .routeId("OrdersEntityRoute")
                .log("${body}")
                .setProperty("initialBody", simple("${body}"))
                .unmarshal().json(JsonLibrary.Jackson, Orders.class)
                .process(exchange -> {
                    Orders orders = exchange.getIn().getBody(Orders.class);
                    exchange.getIn().setBody(orders);
                })
                .log("${body}")
                .to("jpa:com.example.sistemapedidos.domain.repositories.IOrdersRepository.saveAll()")
                .log("${body}")

                .setProperty("ORDER_CODE", simple("${body.code}"))
                .setBody(simple("${body.itens}"))
                .split(body())
                .process(exchange -> {
                    OrderItens item = exchange.getIn().getBody(OrderItens.class);
                    exchange.getIn().setBody(item);
                })
                .setProperty("QTD", simple("${body.amount}"))
                .log(INFO, "${body}")

                .setBody(simple(RETRIVE_VALUES))
                .to("jdbc:dataSource")
                .log("${body}")
                .setProperty("product_price", simple("${body[0].[price]}"))
                .bean(CalculaTotalBean.class, "getPrice(${body[0].[price]}, ${exchangeProperty.QTD})")
                .log("${body}")

                .setBody(simple(UPDATE_SUBTOTAL))
                .to("jdbc:dataSource")
                .log("${body}")
                .end()

        ;
    }

    class CalculaTotalBean {
        public double getPrice(Double price, Double qtd) {
            subtotal += qtd * price;
            return subtotal;
        }
    }
}
