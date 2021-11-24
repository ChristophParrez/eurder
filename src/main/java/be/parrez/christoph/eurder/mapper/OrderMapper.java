package be.parrez.christoph.eurder.mapper;

import be.parrez.christoph.eurder.dto.*;
import be.parrez.christoph.eurder.model.OrderItem;
import be.parrez.christoph.eurder.model.Order;
import be.parrez.christoph.eurder.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    @Autowired
    private ItemRepository itemRepository;

    public OrderDto toDto(Order entity) {
        return new OrderDto(entity.getOrderId(), this.toItemDto(entity.getItems()), entity.getCustomerId(), entity.getTotalPrice());
    }

    public OrderItemDto toDto(OrderItem entity) {
        return new OrderItemDto(entity.getOrderItemId(), entity.getAmount(), entity.getShippingDate(), new OriginalItemDto(entity.getOriginalItemId(), entity.getOriginalItemName(), entity.getOriginalItemDescription(), entity.getOriginalItemPrice()));
    }

    public List<OrderDto> toDto(List<Order> orders) {
        return orders.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<OrderItemDto> toItemDto(List<OrderItem> orderItems) {
        return orderItems.stream().map(this::toDto).collect(Collectors.toList());
    }

    public OrderReportDto toReportDto(List<OrderDto> orders) {
        return new OrderReportDto(orders);
    }

    public OrderItem toEntity(ItemGroupCreateDto dto) {
        return new OrderItem(itemRepository.findByItemId(dto.getItemId()), dto.getAmount());
    }

    public List<OrderItem> toEntity(List<ItemGroupCreateDto> itemsDto) {
        return itemsDto.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
