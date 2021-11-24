package be.parrez.christoph.eurder.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "customer_id")
    private String customerId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    public Order(String customerId, List<OrderItem> items) {
        this.orderId = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.items = items;
    }

    public Order() {

    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void add(OrderItem itemGroup) {
        items.add(itemGroup);
    }

    public void remove(int id) {
        items.remove(id);
    }

    public double getTotalPrice() {
        return this.items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    }
}
