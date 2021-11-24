package be.parrez.christoph.eurder.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @Column(name = "order_item_id")
    private String orderItemId;

    @Column(name = "amount")
    private int amount;

    @Column(name = "shipping_date")
    private LocalDate shippingDate;

    @Column(name = "original_item_id")
    private String originalItemId;

    @Column(name = "original_item_name")
    private String originalItemName;

    @Column(name = "original_item_description")
    private String originalItemDescription;

    @Column(name = "original_item_price")
    private Double originalItemPrice;

    public OrderItem(Item item, int amount) {
        this.orderItemId = UUID.randomUUID().toString();
        this.amount = amount;
        this.shippingDate = calculateShippingDate(item, amount);
        if (item != null) {
            this.originalItemId = item.getItemId();
            this.originalItemName = item.getName();
            this.originalItemDescription = item.getDescription();
            this.originalItemPrice = item.getPrice();
        }
    }

    public OrderItem() {

    }

    private LocalDate calculateShippingDate(Item item, int amountNeeded) {
        if (item == null) return null;
        return item.getStock() >= amountNeeded ? LocalDate.now().plusDays(1) : LocalDate.now().plusDays(7);
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public String getOriginalItemId() {
        return originalItemId;
    }

    public String getOriginalItemName() {
        return originalItemName;
    }

    public String getOriginalItemDescription() {
        return originalItemDescription;
    }

    public Double getOriginalItemPrice() {
        return originalItemPrice;
    }

    public double getTotalPrice() {
        if (originalItemPrice == null) return 0;
        return originalItemPrice * amount;
    }
}
