package be.parrez.christoph.eurder.repository;

import be.parrez.christoph.eurder.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, String> {

    Order findByOrderId(String orderId);

    List<Order> findAll();

    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // private final Map<String, Order> orderRepository;
    //
    // public OrderRepository() {
    //     this.orderRepository = new HashMap<>();
    // }
    //
    // public void save(Order order) {
    //     orderRepository.put(order.getId(), order);
    //     logger.info("Saved new order to repository with id " + order.getId());
    // }
    //
    // public Order getEntry(String key) {
    //     return orderRepository.get(key);
    // }
    //
    // public List<Order> getEntries() {
    //     return orderRepository.values().stream().toList();
    // }
}
