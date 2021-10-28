package be.parrez.christoph.eurder.mapper;

import be.parrez.christoph.eurder.dto.ItemGroupCreateDto;
import be.parrez.christoph.eurder.dto.OrderDto;
import be.parrez.christoph.eurder.model.ItemGroup;
import be.parrez.christoph.eurder.model.Order;
import be.parrez.christoph.eurder.dto.OrderReportDto;
import be.parrez.christoph.eurder.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    @Autowired
    private ItemService itemService;

    public OrderDto toDto(Order entity) {
        return new OrderDto(entity.getId(), entity.getItems(), entity.getCustomerId(), entity.getTotalPrice());
    }

    public List<OrderDto> toDto(List<Order> orders) {
        return orders.stream().map(this::toDto).collect(Collectors.toList());
    }

    public OrderReportDto toReportDto(List<OrderDto> orders) {
        return new OrderReportDto(orders);
    }

    public ItemGroup toEntity(ItemGroupCreateDto dto) {
        return new ItemGroup(itemService.getItemFromDatabase(dto.getItemId()), dto.getAmount());
    }

    public List<ItemGroup> toEntity(List<ItemGroupCreateDto> itemsDto) {
        return itemsDto.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
