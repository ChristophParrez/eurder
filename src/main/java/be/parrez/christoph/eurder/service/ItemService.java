package be.parrez.christoph.eurder.service;

import be.parrez.christoph.eurder.dto.ItemCreateDto;
import be.parrez.christoph.eurder.dto.ItemDto;
import be.parrez.christoph.eurder.exceptions.BadRequestException;
import be.parrez.christoph.eurder.mapper.ItemMapper;
import be.parrez.christoph.eurder.model.Item;
import be.parrez.christoph.eurder.model.UserRole;
import be.parrez.christoph.eurder.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final UserService userService;

    private static final String VALID_EMAIL_ADDRESS_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper, UserService userService) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.userService = userService;
        this.addDummyData();
    }

    public void addDummyData() {
        Item item0 = new Item("Item1", "Description1", 6.99, 2);
        Item item1 = new Item("Item2", "Description2", 7.99, 4);
        Item item2 = new Item("Item3", "Description3", 8.99, 6);
        Item item3 = new Item("Item4", "Description4", 9.99, 8);
        this.itemRepository.getRepository().put(item0.getId(), item0);
        this.itemRepository.getRepository().put(item1.getId(), item1);
        this.itemRepository.getRepository().put(item2.getId(), item2);
        this.itemRepository.getRepository().put(item3.getId(), item3);
    }

    public List<ItemDto> getItems() {
        return itemMapper.toDto(itemRepository.getRepository().values().stream().toList());
    }

    public ItemDto addItem(String authorizedId, ItemCreateDto itemDto) {
        userService.assertUserPermissions(authorizedId, UserRole.ADMIN, "You are not authorized to add items.");

        Item newItem = itemMapper.toEntity(itemDto);
        itemRepository.getRepository().put(newItem.getId(), newItem);
        logger.info("Created new item with id " + newItem.getId());

        return itemMapper.toDto(newItem);
    }

    public ItemDto updateItem(String authorizedId, String itemId, ItemCreateDto itemDto) {
        userService.assertUserPermissions(authorizedId, UserRole.ADMIN, "You are not authorized to update items.");

        Item itemUpdate = itemRepository.getRepository().get(itemId);
        if (itemUpdate == null) throw new BadRequestException("The item with id " + itemId + " could not be found.", logger);

        itemUpdate.setName(itemDto.getName());
        itemUpdate.setAmount(itemDto.getAmount());
        itemUpdate.setDescription(itemDto.getDescription());
        itemUpdate.setPrice(itemDto.getPrice());

        itemRepository.getRepository().put(itemId, itemUpdate);
        logger.info("Updated item with id " + itemId);

        return itemMapper.toDto(itemUpdate);
    }
}
