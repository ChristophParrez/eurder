package be.parrez.christoph.eurder.dto;

import be.parrez.christoph.eurder.model.OrderItem;

import java.util.List;

public class OrderDto {
    private final String orderId;
    private final String customerId;
    private final List<OrderItemDto> items;
    private final double totalPrice;
    // private final OriginalItemDto originalItem;

    public OrderDto(String orderId, List<OrderItemDto> items, String customerId, double totalPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
