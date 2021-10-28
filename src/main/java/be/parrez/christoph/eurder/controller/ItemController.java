package be.parrez.christoph.eurder.controller;

import be.parrez.christoph.eurder.dto.ItemCreateDto;
import be.parrez.christoph.eurder.dto.ItemDto;
import be.parrez.christoph.eurder.dto.ItemStockDto;
import be.parrez.christoph.eurder.dto.ItemUpdateDto;
import be.parrez.christoph.eurder.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "items")
public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> getAll() {
        logger.info("Incoming get item list request");
        return itemService.getItems();
    }

    @GetMapping(path = "/stock", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemStockDto> getById(@RequestHeader(required = false) String authorizedId, @RequestParam(required = false) String filter) {
        logger.info("Incoming get stock list request");
        return itemService.getStockOverview(authorizedId, filter);
    }

    // @GetMapping(path = "/{uuid}", produces = "application/json")
    // @ResponseStatus(HttpStatus.OK)
    // public UserDto getById(@RequestHeader(value = "uuid", required = false) String adminId, @PathVariable String customerId) {
    //     return userService.getCustomer(adminId, customerId);
    // }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto add(@RequestHeader(required = false) String authorizedId, @RequestBody ItemCreateDto itemDto) {
        logger.info("Incoming item add request");
        return itemService.addItem(authorizedId, itemDto);
    }

    @PutMapping(path = "/{itemId}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ItemDto update(@RequestHeader(required = false) String authorizedId,
                          @RequestBody ItemUpdateDto itemDto,
                          @PathVariable String itemId) {
        logger.info("Incoming item update request");
        return itemService.updateItem(authorizedId, itemId, itemDto);
    }
}
