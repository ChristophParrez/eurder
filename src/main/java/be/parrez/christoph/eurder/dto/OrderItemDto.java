package be.parrez.christoph.eurder.dto;

import java.time.LocalDate;

public class OrderItemDto {

    private final String orderItemId;
    private final int amount;
    private final LocalDate shippingDate;
    private final OriginalItemDto item;

    public OrderItemDto(String orderItemId, int amount, LocalDate shippingDate, OriginalItemDto item) {
        this.orderItemId = orderItemId;
        this.amount = amount;
        this.shippingDate = shippingDate;
        this.item = item;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public OriginalItemDto getItem() {
        return item;
    }
}
