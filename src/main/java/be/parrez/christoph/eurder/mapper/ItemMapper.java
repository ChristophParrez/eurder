package be.parrez.christoph.eurder.mapper;

import be.parrez.christoph.eurder.controller.ItemController;
import be.parrez.christoph.eurder.dto.ItemCreateDto;
import be.parrez.christoph.eurder.dto.ItemDto;
import be.parrez.christoph.eurder.dto.ItemStockDto;
import be.parrez.christoph.eurder.dto.ItemUpdateDto;
import be.parrez.christoph.eurder.model.Item;
import be.parrez.christoph.eurder.model.ItemStockLevel;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemMapper {
    public Item toEntity(ItemCreateDto dto) {
        return new Item(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getAmount());
    }

    public List<ItemDto> toDto(List<Item> items) {
        return items.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ItemDto toDto(Item entity) {
        return new ItemDto(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getAmount());
    }

    public List<ItemStockDto> toStockDto(List<Item> items, String filter) {
        return items.stream().map(this::toStockDto)
                .filter(dto -> {
                    if (filter == null) return true;
                    return dto.getStockLevel().toString().matches("(?i).*" + filter + "(.*)");
                })
                .sorted(Comparator.comparingInt(ItemStockDto::getAmount))
                .collect(Collectors.toList());
    }

    public ItemStockDto toStockDto(Item entity) {
        ItemStockLevel stockLevel;
        if (entity.getAmount() < 5) stockLevel = ItemStockLevel.STOCK_LOW;
        else if (entity.getAmount() < 10) stockLevel = ItemStockLevel.STOCK_MEDIUM;
        else stockLevel = ItemStockLevel.STOCK_HIGH;
        return new ItemStockDto(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getAmount(), stockLevel);
    }
}
