package be.parrez.christoph.eurder.dto;

public class OriginalItemDto {

    private final String id;
    private final String name;
    private final String description;
    private final double price;

    public OriginalItemDto(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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
