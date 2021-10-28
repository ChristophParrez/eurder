package be.parrez.christoph.eurder.repository;

import be.parrez.christoph.eurder.model.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository {
    private final Map<String, Order> orderRepository;

    public OrderRepository() {
        this.orderRepository = new HashMap<>();
    }

    public Map<String, Order> getRepository() {
        return orderRepository;
    }
}
