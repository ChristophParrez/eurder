package be.parrez.christoph.eurder.service;

import be.parrez.christoph.eurder.dto.ItemDto;
import be.parrez.christoph.eurder.mapper.ItemMapper;
import be.parrez.christoph.eurder.model.Item;
import be.parrez.christoph.eurder.model.UserRole;
import be.parrez.christoph.eurder.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ItemDto> getItems(String userId) {
        return itemMapper.toDto(new ArrayList<>(itemRepository.getRepository().values()));
    }

    public ItemDto addItem(ItemDto itemDto) {
        if (checkUserRole(adminId, UserRole.ADMIN)) {
            logger.warn("getCustomer -> User is not admin");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to get the customer details.");
        }

        Item newItem = itemMapper.

        return itemMapper.toDto(new Item());
    }
}
