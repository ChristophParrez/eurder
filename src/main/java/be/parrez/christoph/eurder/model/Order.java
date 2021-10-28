package be.parrez.christoph.eurder.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private final String id;
    private final String customerId;
    private final List<ItemGroup> items;

    public Order(String customerId) {
        this(customerId, new ArrayList<>());
    }

    public Order(String customerId, List<ItemGroup> items) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<ItemGroup> getItems() {
        return items;
    }

    public void add(ItemGroup itemGroup) {
        items.add(itemGroup);
    }

    public void remove(int id) {
        items.remove(id);
    }

    public double getTotalPrice() {
        return this.items.stream().mapToDouble(ItemGroup::getTotalPrice).sum();
    }
}
