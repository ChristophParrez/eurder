package be.parrez.christoph.eurder.service;

import be.parrez.christoph.eurder.dto.ItemCreateDto;
import be.parrez.christoph.eurder.dto.ItemDto;
import be.parrez.christoph.eurder.dto.ItemStockDto;
import be.parrez.christoph.eurder.dto.ItemUpdateDto;
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

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper, UserService userService) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.userService = userService;
        this.addDummyData();
    }

    private void addDummyData() {
        Item item0 = new Item("dummy-item-id-1", "Item1", "Description1", 6.99, 0);
        Item item1 = new Item("dummy-item-id-2", "Item2", "Description2", 7.99, 4);
        Item item2 = new Item("dummy-item-id-3", "Item3", "Description3", 8.99, 6);
        Item item3 = new Item("dummy-item-id-4", "Item4", "Description4", 9.99, 8);
        Item item4 = new Item("dummy-item-id-5", "Item5", "Description5", 11.99, 10);
        Item item5 = new Item("dummy-item-id-6", "Item6", "Description6", 13.99, 12);
        itemRepository.save(item0);
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);
    }

    public List<ItemDto> getAllItems() {
        return itemMapper.toDto(itemRepository.getEntries());
    }

    public List<ItemStockDto> getStockOverview(String authorizedId, String filter) {
        userService.assertUserPermissions(authorizedId, UserRole.ADMIN, "You are not authorized to get the items stock.");
        return itemMapper.toStockDto(itemRepository.getEntries(), filter);
    }

    public ItemDto addItem(String authorizedId, ItemCreateDto itemDto) {
        userService.assertUserPermissions(authorizedId, UserRole.ADMIN, "You are not authorized to add items.");

        Item newItem = itemMapper.toEntity(itemDto);
        itemRepository.save(newItem);

        return itemMapper.toDto(newItem);
    }

    public ItemDto updateItem(String authorizedId, String itemId, ItemUpdateDto itemDto) {
        userService.assertUserPermissions(authorizedId, UserRole.ADMIN, "You are not authorized to update items.");

        Item itemUpdate = itemRepository.getEntry(itemId);
        if (itemUpdate == null) throw new BadRequestException("The item with id " + itemId + " could not be found.", logger);

        itemUpdate.setName(itemDto.getName());
        itemUpdate.setAmount(itemDto.getAmount());
        itemUpdate.setDescription(itemDto.getDescription());
        itemUpdate.setPrice(itemDto.getPrice());

        itemRepository.save(itemUpdate);
        logger.info("Updated item with id " + itemId);

        return itemMapper.toDto(itemUpdate);
    }
}
