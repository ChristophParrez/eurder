package be.parrez.christoph.eurder.repository;

import be.parrez.christoph.eurder.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Map<String, Order> orderRepository;

    public OrderRepository() {
        this.orderRepository = new HashMap<>();
    }

    public void save(Order order) {
        orderRepository.put(order.getId(), order);
        logger.info("Saved new order to repository with id " + order.getId());
    }

    public Order getEntry(String key) {
        return orderRepository.get(key);
    }

    public List<Order> getEntries() {
        return orderRepository.values().stream().toList();
    }
}
