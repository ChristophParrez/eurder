package be.parrez.christoph.eurder.controller;

import be.parrez.christoph.eurder.dto.UserDto;
import be.parrez.christoph.eurder.dto.UserRegisterDto;
import be.parrez.christoph.eurder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;

    @Autowired
    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAll(@RequestHeader(value = "uuid", required = false) String uuid) {
        return userService.getCustomers(uuid);
    }

    @GetMapping(path = "/{uuid}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getById(@RequestHeader(value = "uuid", required = false) String adminId, @PathVariable(value = "uuid") String customerId) {
        return userService.getCustomer(adminId, customerId);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto add(@RequestBody UserRegisterDto userDto) {
        logger.info("Incoming user register request");
        return userService.registerCustomer(userDto);
    }
}
