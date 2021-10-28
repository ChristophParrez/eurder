package be.parrez.christoph.eurder.dto;

import be.parrez.christoph.eurder.model.Item;

public class ItemDataCopy {
    private final String id;
    private final String name;
    private final String description;
    private final double price;

    public ItemDataCopy(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
