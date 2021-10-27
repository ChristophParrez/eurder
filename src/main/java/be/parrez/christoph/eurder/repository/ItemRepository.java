package be.parrez.christoph.eurder.repository;

import be.parrez.christoph.eurder.model.Item;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {
    private final Map<String, Item> itemRepository;

    public ItemRepository() {
        this.itemRepository = new HashMap<>();
    }

    public Map<String, Item> getRepository() {
        return itemRepository;
    }
}
