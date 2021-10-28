package be.parrez.christoph.eurder.dto;

public class ItemGroupCreateDto {
    private final String itemId;
    private final int amount;

    public ItemGroupCreateDto(String itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }
}
