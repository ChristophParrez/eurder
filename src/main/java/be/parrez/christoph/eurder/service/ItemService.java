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
    }

    public List<ItemDto> getAllItems() {
        return itemMapper.toDto(itemRepository.findAll());
    }

    public List<ItemStockDto> getStockOverview(String authorizedId, String filter) {
        userService.assertUserPermissions(authorizedId, UserRole.ADMIN, "You are not authorized to get the items stock.");
        return itemMapper.toStockDto(itemRepository.findAll(), filter);
    }

    public ItemDto addItem(String authorizedId, ItemCreateDto itemDto) {
        userService.assertUserPermissions(authorizedId, UserRole.ADMIN, "You are not authorized to add items.");

        Item newItem = itemMapper.toEntity(itemDto);
        itemRepository.save(newItem);

        return itemMapper.toDto(newItem);
    }

    public ItemDto updateItem(String authorizedId, String itemId, ItemUpdateDto itemDto) {
        userService.assertUserPermissions(authorizedId, UserRole.ADMIN, "You are not authorized to update items.");

        Item itemUpdate = itemRepository.findByItemId(itemId);
        if (itemUpdate == null) throw new BadRequestException("The item with id " + itemId + " could not be found.", logger);

        itemUpdate.setName(itemDto.getName());
        itemUpdate.setStock(itemDto.getAmount());
        itemUpdate.setDescription(itemDto.getDescription());
        itemUpdate.setPrice(itemDto.getPrice());

        itemRepository.save(itemUpdate);
        logger.info("Updated item with id " + itemId);

        return itemMapper.toDto(itemUpdate);
    }
}
