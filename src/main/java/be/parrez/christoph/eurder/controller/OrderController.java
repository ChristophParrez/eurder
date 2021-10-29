package be.parrez.christoph.eurder.controller;

import be.parrez.christoph.eurder.dto.*;
import be.parrez.christoph.eurder.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "orders")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public OrderReportDto getOrderReport(@RequestHeader(required = false) String authorizedId) {
        logger.info("Incoming get orders list request");
        return orderService.getOrderReport(authorizedId);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto add(@RequestHeader(required = false) String authorizedId,
                        @RequestBody OrderCreateDto orderDto) {
        logger.info("Incoming new order request");
        return orderService.createOrder(authorizedId, orderDto);
    }

    @PostMapping(path = "/{orderId}", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto add(@RequestHeader(required = false) String authorizedId,
                        @PathVariable String orderId) {
        logger.info("Incoming new order request");
        return orderService.reCreateOrder(authorizedId, orderId);
    }

}
