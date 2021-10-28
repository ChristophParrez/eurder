package be.parrez.christoph.eurder.dto;

import java.util.List;

public class OrderReportDto {
    private final String name = "Report of orders";
    private final double totalPriceOfAllOrders;
    private final List<OrderDto> orders;

    public OrderReportDto(List<OrderDto> orders) {
        this.orders = orders;
        this.totalPriceOfAllOrders = orders.stream().mapToDouble(OrderDto::getTotalPrice).sum();
    }

    public String getName() {
        return name;
    }

    public double getTotalPriceOfAllOrders() {
        return totalPriceOfAllOrders;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }
}
