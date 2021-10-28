package be.parrez.christoph.eurder.dto;

import be.parrez.christoph.eurder.model.OrderCreateItem;

import java.util.List;

public class OrderCreateDto {
    // private final List<OrderCreateItem> items;

    // public OrderCreateDto(List<OrderCreateItem> items) {
    //     this.items = items;
    // }
    //
    // public List<OrderCreateItem> getItems() {
    //     return items;
    // }

    private final String items;

    public OrderCreateDto(String items) {
        this.items = items;
    }

    public String getItems() {
        return items;
    }
}
