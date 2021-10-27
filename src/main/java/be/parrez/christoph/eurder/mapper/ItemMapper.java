package be.parrez.christoph.eurder.mapper;

import be.parrez.christoph.eurder.dto.ItemDto;
import be.parrez.christoph.eurder.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemMapper {
    public Item toEntity(ItemDto dto) {

    }

    public List<ItemDto> toDto(List<Item> items) {
        return items.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ItemDto toDto(Item entity) {
        return new ItemDto(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getAmount());
    }
}
