package be.parrez.christoph.eurder.dto;

import be.parrez.christoph.eurder.service.ItemService;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.beans.factory.annotation.Autowired;

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
