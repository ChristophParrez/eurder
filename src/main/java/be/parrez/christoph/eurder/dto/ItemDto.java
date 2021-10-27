package be.parrez.christoph.eurder.dto;

import java.util.UUID;

public class ItemDto {
    private final String id;
    private final String name;
    private final String description;
    private final double price;
    private final int amount;

    public ItemDto(String id, String name, String description, double price, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }
}
