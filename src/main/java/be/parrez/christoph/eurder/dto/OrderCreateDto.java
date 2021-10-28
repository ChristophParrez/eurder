package be.parrez.christoph.eurder.dto;

import be.parrez.christoph.eurder.model.OrderCreateItem;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class OrderCreateDto {
    private final List<OrderCreateItem> items;

    @JsonCreator // I GET AN ERROR WITHOUT THIS, WHY???
    public OrderCreateDto(List<OrderCreateItem> items) {
        this.items = items;
    }

    public List<OrderCreateItem> getItems() {
        return items;
    }
}
