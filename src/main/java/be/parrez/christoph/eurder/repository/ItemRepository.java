package be.parrez.christoph.eurder.repository;

import be.parrez.christoph.eurder.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, String> {

    Item findByItemId(String itemId);

    List<Item> findAll();

    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // private final Map<String, Item> itemRepository;
    //
    // public ItemRepository() {
    //     this.itemRepository = new HashMap<>();
    // }
    //
    // public void save(Item item) {
    //     itemRepository.put(item.getId(), item);
    //     logger.info("Saved new item to repository with id " + item.getId());
    // }
    //
    // public Item getEntry(String key) {
    //     return itemRepository.get(key);
    // }
    //
    // public List<Item> getEntries() {
    //     return itemRepository.values().stream().toList();
    // }
}
