package be.parrez.christoph.eurder.service;

import be.parrez.christoph.eurder.dto.OrderCreateDto;
import be.parrez.christoph.eurder.dto.OrderDto;
import be.parrez.christoph.eurder.dto.OrderReportDto;
import be.parrez.christoph.eurder.exceptions.BadRequestException;
import be.parrez.christoph.eurder.mapper.OrderMapper;
import be.parrez.christoph.eurder.model.*;
import be.parrez.christoph.eurder.repository.ItemRepository;
import be.parrez.christoph.eurder.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        ItemRepository itemRepository,
                        OrderMapper orderMapper,
                        UserService userService) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.orderMapper = orderMapper;
        this.userService = userService;
    }

    public OrderReportDto getOrderReport(String authorizedId) {
        logger.info("Getting order report with authorizedId " + authorizedId);
        User user = userService.assertUserPermissions(authorizedId, List.of(UserRole.ADMIN, UserRole.CUSTOMER), "You are not authorized to see orders.");
        return orderMapper.toReportDto(getAllOrders(user));
    }

    public List<OrderDto> getAllOrders(User user) {
        logger.info("Getting orders with authorizedId " + user.getId());
        return orderMapper.toDto(
                orderRepository.findAll().stream()
                        .filter(order -> {
                            if (user.getUserRole().equals(UserRole.ADMIN)) {
                                return true;
                            }
                            return order.getCustomerId().equals(user.getId());
                        })
                        .toList()
        );
    }

    public OrderDto createOrder(String authorizedId, OrderCreateDto orderCreateDto) {
        userService.assertUserPermissions(authorizedId, List.of(UserRole.ADMIN, UserRole.CUSTOMER), "You are not authorized to create orders.");

        logger.info("Trying to order --> " + orderCreateDto.getItems().stream().map(item -> item.getItemId() + " x " + item.getAmount()).collect(Collectors.joining(", ")) + " as user with id " + authorizedId);

        Order newOrder = new Order(authorizedId, orderMapper.toEntity(orderCreateDto.getItems()));
        orderRepository.save(newOrder);
        return orderMapper.toDto(newOrder);
    }

    public OrderDto reCreateOrder(String authorizedId, String orderId) {
        logger.info("User with id " + authorizedId + " trying to recreate order " + orderId);

        Order order = orderRepository.findByOrderId(orderId);
        if (order == null)
            throw new BadRequestException("The order with id " + orderId + " was not found in the system.");

        final Deque<String> errorMessages = new LinkedList<>();
        List<OrderItem> orderItems = order.getItems().stream()
                .filter(orderItem -> {
                    Item item = itemRepository.findByItemId(orderItem.getOriginalItemId());
                    if (item == null) {
                        errorMessages.add("Item with id " + orderItem.getOriginalItemId() + " is no longer in the system.");
                        return false;
                    }
                    if (item.getStock() < orderItem.getAmount()) {
                        errorMessages.add("Item with id " + orderItem.getOriginalItemId() + " has less than " + orderItem.getAmount() + " in stock.");
                        return false;
                    }
                    return true;
                })
                .map(orderItem -> new OrderItem(itemRepository.findByItemId(orderItem.getOriginalItemId()), orderItem.getAmount()))
                .toList();

        orderItems.forEach(orderItem -> System.out.println(orderItem.getOriginalItemId() + " x " + orderItem.getAmount()));

        if (orderItems.size() == 0)
            errorMessages.add("None of the items are still in the system.");

        if (errorMessages.size() > 0) {
            errorMessages.addFirst("Order could not be recreated. Reasons:");
            errorMessages.forEach(logger::error);
            throw new BadRequestException(String.join(" ", errorMessages));
        }

        // return null;
        Order newOrder = new Order(authorizedId, orderItems);
        orderRepository.save(newOrder);
        return orderMapper.toDto(newOrder);
    }
}
