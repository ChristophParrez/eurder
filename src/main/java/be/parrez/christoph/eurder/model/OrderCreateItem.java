package be.parrez.christoph.eurder.model;

public class OrderCreateItem {
    private final String itemId;
    private final int amount;

    public OrderCreateItem(String itemId, int amount) {
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
