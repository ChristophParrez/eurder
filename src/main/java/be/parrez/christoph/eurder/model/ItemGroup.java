package be.parrez.christoph.eurder.model;

import be.parrez.christoph.eurder.dto.ItemDataCopy;
import be.parrez.christoph.eurder.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class ItemGroup {
    private final ItemDataCopy item;
    private final int amount;
    private final LocalDate shippingDate;

    public ItemGroup(Item item, int amount) {
        this.amount = amount;
        this.shippingDate = calculateShippingDate(item, amount);
        this.item = item != null ? new ItemDataCopy(item) : null;
    }

    private LocalDate calculateShippingDate(Item item, int amountNeeded) {
        if (item == null) return null;
        return item.getAmount() >= amountNeeded ? LocalDate.now().plusDays(1) : LocalDate.now().plusDays(7);
    }

    public ItemDataCopy getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double getTotalPrice() {
        if (item == null) return 0;
        return item.getPrice() * amount;
    }
}
