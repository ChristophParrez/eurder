package be.parrez.christoph.eurder.dto;

import be.parrez.christoph.eurder.model.ItemGroup;

import java.util.List;

public class OrderDto {
    private final String orderId;
    private final String customerId;
    private final List<ItemGroup> items;
    private final double totalPrice;

    public OrderDto(String orderId, List<ItemGroup> items, String customerId, double totalPrice) {
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

    public List<ItemGroup> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
