package be.parrez.christoph.eurder.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class OrderCreateDto {

    private final List<ItemGroupCreateDto> items;

    @JsonCreator
    public OrderCreateDto(List<ItemGroupCreateDto> items) {
        this.items = items;
    }

    public List<ItemGroupCreateDto> getItems() {
        return items;
    }
}
