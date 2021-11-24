package be.parrez.christoph.eurder.dto;

public class ItemCreateDto {
    private final String name;
    private final String description;
    private final double price;
    private final int stock;

    public ItemCreateDto(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }
}
