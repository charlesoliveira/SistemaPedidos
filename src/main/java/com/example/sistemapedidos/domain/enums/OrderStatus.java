package com.example.sistemapedidos.domain.enums;

public enum OrderStatus {

    WAITING_VALUES(1),
    VALUED(2),
    CANCELLED(3);

    private int code;

    private OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderStatus valueOf(int code) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.getCode() == code) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("Order status not found");
    }
}
