package be.parrez.christoph.eurder.dto;

import be.parrez.christoph.eurder.model.ItemStockLevel;

public class ItemStockDto {
    private final String id;
    private final String name;
    private final String description;
    private final double price;
    private final int amount;
    private final ItemStockLevel stockLevel;

    public ItemStockDto(String id, String name, String description, double price, int amount, ItemStockLevel stockLevel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.stockLevel = stockLevel;
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

    public ItemStockLevel getStockLevel() {
        return stockLevel;
    }
}
