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


    // @Autowired
    private ItemService itemService;

    public ItemGroup(Item item, int amount) {
        this.amount = amount;
        this.shippingDate = calculateShippingDate(item, amount);
        this.item = new ItemDataCopy(item);
    }

    private LocalDate calculateShippingDate(Item item, int amountNeeded) {
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
        return item.getPrice() * amount;
    }
}
